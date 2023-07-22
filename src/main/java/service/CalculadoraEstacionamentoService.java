package service;

import model.model.Permanencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CalculadoraEstacionamentoService {

    public Double valorPago (double permanencia) {

        Double valorPago = 0.0;

        if (permanencia <=60) {

            valorPago = Permanencia.UMA_HORA.getValor();

        } else if (permanencia >= 720) {

            valorPago = Permanencia.MEIO_PERIODO.getValor();


        } else  {


            valorPago = (Permanencia.TRINTA_MINUTOS.getValor() *  adicionalDePermanencia(permanencia))
            + Permanencia.UMA_HORA.getValor();

        }

        return valorPago;

    }

    public double adicionalDePermanencia(double permanencia) {

        double adicional = (int) Math.ceil((permanencia - 60)/30);

        return adicional;
    }

    public Double calculaPermanencia (LocalTime entrada, LocalTime saida){

        double permanencia = entrada.until(saida, ChronoUnit.MINUTES);

        return permanencia;
    }
}

