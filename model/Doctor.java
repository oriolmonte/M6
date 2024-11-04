package model;

public class Doctor {
	
	public Doctor(int codi, int codiHospital, String nom) {
		this.codi = codi;
		this.codiHospital = codiHospital;
		this.nom = nom;
	}
	private int codi;
	private int codiHospital;
	private String nom;
	
	public int getCodi() {
		return codi;
	}
	public void setCodi(int codi) {
		this.codi = codi;
	}
	public int getCodiHospital() {
		return codiHospital;
	}
	public void setCodiHospital(int codiHospital) {
		this.codiHospital = codiHospital;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Doctor [codi=" + codi + ", codiHospital=" + codiHospital + ", nom=" + nom + "]";
	}
	
	
}
