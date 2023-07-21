package deep_estacionamento.model;

import java.time.LocalTime;
import java.util.List;

public class Estacionamento {
    private Integer id;
    private LocalTime dataEntrada;
    private LocalTime dataSaida;
    private Double permanencia;
    private Double valorPago;
    private List<Carro> carros;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalTime dataSaida) {
        this.dataSaida = dataSaida;
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
