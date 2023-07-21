package deep_estacionamento.model;

public enum Permanencia {

    UMA_HORA(10.00),
    TRINTA_MINUTOS(2.00),
    MEIO_PERIODO(90.00);

    private Double valor;

    Permanencia(Double valor) {

        this.valor = valor;

    }

    public Double getValor() {
        return valor;
    }
}
