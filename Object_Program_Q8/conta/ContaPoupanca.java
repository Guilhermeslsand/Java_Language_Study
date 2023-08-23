package br.ufc.poo.conta;

import br.ufc.poo.conta.excecao.TNPException;
import br.ufc.poo.conta.excecao.VNException;
import java.io.*;

public class ContaPoupanca extends Conta implements Serializable{
  private static final long serialVersionUID = 1L;
  
  public ContaPoupanca(String numero) {
    super(numero);
  }

  public void renderJuros(double taxa) throws TNPException {
    try {
      super.creditar(getSaldo() * taxa);
    } catch (VNException vne) {
      throw new TNPException(this.numero, taxa);
    }
  }
}