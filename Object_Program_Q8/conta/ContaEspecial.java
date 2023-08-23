package br.ufc.poo.conta;

import br.ufc.poo.conta.excecao.PNBException;
import br.ufc.poo.conta.excecao.VNException;
import java.io.*;

public class ContaEspecial extends Conta implements Serializable{
  private double bonus;
  private double percentagem = 0.01;
  private static final long serialVersionUID = 1L;

  public ContaEspecial(String numero) {
    super(numero);
    this.bonus = 0.0;
  }

  public void renderBonus() {
    try {
      super.creditar(this.bonus);
      this.bonus = 0.0;
    } catch (VNException vne) {

    }
  }

  public void creditar(double valor) throws VNException {
    if (valor < 0.0) {
      throw new VNException(this.numero, valor);
    }

    this.bonus = this.bonus + (valor * this.percentagem);
    super.creditar(valor);

  }

  public double getBonus() {
    return this.bonus;
  }

  public double getBonusPercentagem() {
    return this.percentagem;
  }

  public void setBonusPercentagem(double percentagem) throws PNBException {
    if (percentagem < 0.0) {
      throw new PNBException(this.numero, percentagem);
    }
    this.percentagem = percentagem;
  }
}