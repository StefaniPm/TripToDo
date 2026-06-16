package com.triptodo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Atividade {
    int id;
    String titulo, descricao, local;
    LocalDate data;
    LocalTime horario;
    Destino destino;
}
