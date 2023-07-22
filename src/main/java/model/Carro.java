package model;

public class Carro {
    private Integer id;
    private String proprietario;
    private String marca;
    private String placa;
    private Boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProprietario() {

        return proprietario;
    }

    public void setProprietario(String proprietario) {

        this.proprietario = proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", proprietario='" + proprietario + '\'' +
                ", marca='" + marca + '\'' +
                ", placa='" + placa + '\'' +
                ", estado=" + estado +
                '}';
    }
}
