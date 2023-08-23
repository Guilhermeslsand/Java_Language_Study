package br.ufc.poo.conta.repo;

import br.ufc.poo.conta.ContaAbstrata;
import br.ufc.poo.conta.repo.excecao.CEException;
import br.ufc.poo.conta.repo.excecao.CIException;
import br.ufc.poo.conta.repo.*;

import java.io.File.*;
import java.io.ObjectOutputStream;

import java.util.Vector;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;
import java.io.*;

public class VectorContaArquivo implements IRepositorioConta {
  private Vector<ContaAbstrata> contas;

  public VectorContaArquivo() {
    this.contas = new Vector<ContaAbstrata>();
  }

  public void inserir(ContaAbstrata conta) throws CEException {
    if (this.existe(conta.getNumero())) {
      throw new CEException(conta.getNumero());
    }
    this.contas.add(conta);
  }

  public void remover(String numero) throws CIException {
    if (!this.existe(numero)) {
      throw new CIException(numero);
    }

    this.contas.remove(this.procurar(numero));
  }

  public ContaAbstrata procurar(String numero) {
    for (ContaAbstrata conta : this.contas) {
      if (conta.getNumero().equals(numero)) {
        return conta;
      }
    }
    return null;
  }

  public ContaAbstrata recuperar(String numero) throws CIException {
    if (!this.existe(numero)) {
      throw new CIException(numero);
    }
    return this.procurar(numero);
  }

  public boolean existe(String numero) {
    return numero != null && this.procurar(numero) != null;
  }

  public ContaAbstrata[] listar() {
    ContaAbstrata[] lista = null;
    if (this.tamanho() > 0) {
      lista = new ContaAbstrata[this.tamanho()];
      int i = 0;
      for (ContaAbstrata conta : this.contas) {
        lista[i++] = conta;
      }
    }
    return lista;
  }

  public int tamanho() {
    return this.contas.size();
  }

  private void serializar() {
    String pathDir = "./arquivo";
    File diretorio = new File(pathDir);
    if(!diretorio.isDirectory()){
      diretorio.mkdir();
    }
    try {
      String pathArquivo = pathDir+"/"+"contas.bin";
      FileOutputStream gravador = new FileOutputStream(pathArquivo);
      ObjectOutputStream conversor = new ObjectOutputStream(gravador);
      conversor.writeObject(contas);
      conversor.close();
    } catch (IOException ioe){
      ioe.printStackTrace();;
    }
  }

  private void desserializar() {
    String pathDir = "./arquivo";
    File diretorio = new File(pathDir);
    if(!diretorio.isDirectory()){
      diretorio.mkdir();
    }

    try{
      String pathArquivo = pathDir+"/"+"contas.bin";
      FileInputStream gravador = new FileInputStream(pathArquivo);
      ObjectInputStream conversor = new ObjectInputStream(gravador);
      contas = (Vector<ContaAbstrata>) conversor.readObject();
      conversor.close();
    } catch (IOException ioe){
      ioe.printStackTrace();
    } catch (ClassNotFoundException e){
      e.printStackTrace();
    }
  }
  
  public void sincronizar(){
    desserializar();
    Timer timer = new Timer();
    TimerTask tarefa = new TimerTask(){
      @Override
      public void run(){
        serializar();
      } 
    };
    timer.schedule(tarefa, 0, 100);
  }
}