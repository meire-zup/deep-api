package model;

import model.Carro;

import java.time.LocalTime;
import java.util.List;

public class Estacionamento {
    private Integer id;
    private LocalTime entrada;
    private LocalTime saida;
    private Double permanencia;
    private Double valorPago;
    private List<Carro> carros;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setDataEntrada(LocalTime dataEntrada) {
        this.entrada = dataEntrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setDataSaida(LocalTime dataSaida) {
        this.saida = dataSaida;
    }

    public Double getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(Double permanencia) {
        this.permanencia = permanencia;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
