package br.com.cliente_crud.util;

import java.util.Date;

public class UtilData {
	/** 
     * Calcula a diferença de duas datas em dias 
     * <br> 
     * <b>Importante:</b> Quando realiza a diferença em dias entre duas datas, este método considera as horas restantes e as converte em fração de dias. 
     * @param dataInicial 
     * @param dataFinal 
     * @return quantidade de dias existentes entre a dataInicial e dataFinal. 
     */  
    public static double diferencaEmDias(Date dataInicial, Date dataFinal){  
        double result = 0;  
        long diferenca = dataFinal.getTime() - dataInicial.getTime();  
        double diferencaEmDias = (diferenca /1000) / 60 / 60 /24; //resultado é diferença entre as datas em dias  
        long horasRestantes = (diferenca /1000) / 60 / 60 %24; //calcula as horas restantes  
        result = diferencaEmDias + (horasRestantes /24d); //transforma as horas restantes em fração de dias  
      
        return result;  
    }  
      
    /** 
     * Calcula a diferença de duas datas em horas 
     * <br> 
     * <b>Importante:</b> Quando realiza a diferença em horas entre duas datas, este método considera os minutos restantes e os converte em fração de horas. 
     * @param dataInicial 
     * @param dataFinal 
     * @return quantidade de horas existentes entre a dataInicial e dataFinal. 
     */  
    public static double diferencaEmHoras(Date dataInicial, Date dataFinal){  
        double result = 0;  
        long diferenca = dataFinal.getTime() - dataInicial.getTime();  
        long diferencaEmHoras = (diferenca /1000) / 60 / 60;  
        long minutosRestantes = (diferenca / 1000)/60 %60;  
        double horasRestantes = minutosRestantes / 60d;  
        result = diferencaEmHoras + (horasRestantes);  
          
        return result;  
    }  
      
    /** 
     * Calcula a diferença de duas datas em minutos 
     * <br> 
     * <b>Importante:</b> Quando realiza a diferença em minutos entre duas datas, este método considera os segundos restantes e os converte em fração de minutos. 
     * @param dataInicial 
     * @param dataFinal 
     * @return quantidade de minutos existentes entre a dataInicial e dataFinal. 
     */  
    public static double diferencaEmMinutos(Date dataInicial, Date dataFinal){  
        double result = 0;  
        long diferenca = dataFinal.getTime() - dataInicial.getTime();  
        double diferencaEmMinutos = (diferenca /1000) / 60; //resultado é diferença entre as datas em minutos  
        long segundosRestantes = (diferenca / 1000)%60; //calcula os segundos restantes  
        result = diferencaEmMinutos + (segundosRestantes /60d); //transforma os segundos restantes em minutos  
      
        return result;  
    }
    
    /** 
     * Calcula a diferença de duas datas em minutos 
     * <br> 
     * <b>Importante:</b> Quando realiza a diferença em minutos entre duas datas, este método considera os segundos restantes e os converte em fração de minutos. 
     * @param dataInicial 
     * @param dataFinal 
     * @return quantidade de minutos existentes entre a dataInicial e dataFinal. 
     */  
    public static double diferencaEmSegundos(Date dataInicial, Date dataFinal){  
        double result = 0;  
        long diferenca = dataFinal.getTime() - dataInicial.getTime();  
        double diferencaEmMinutos = (diferenca /1000) / 60; //resultado é diferença entre as datas em minutos  
        long segundosRestantes = (diferenca / 1000)%60; //calcula os segundos restantes  
        result = diferencaEmMinutos + (segundosRestantes /60d); //transforma os segundos restantes em minutos  
        result = result * 60;
        return result;  
    }
    
    /**
     * @param horaCompleta
     * @return
     */
    public static Long horasToSegundos(String horaCompleta){  
    	Long resultSegundos = new Long(0);

    	  //pego a posição onde esta os (dois pontos ':') e uso (-2) para ver o inicio da hora
    	  int posicao = horaCompleta.indexOf(":") -2;

    	  /*acho as posições exatas da hora, minuto e segundo e jogo em variaveis
    	  não usei direto int aqui, porque o int corta o zero à esquera (exemplo 01 - fica 1)*/
    	  String h = horaCompleta.substring(posicao, posicao +2 );
    	  String m = horaCompleta.substring(posicao+3, posicao +5 );
    	  String s = horaCompleta.substring(posicao+6, posicao +8 );

    	  //faço calculos da hora em minutos depois em segundos.
    	  int hora = Integer.parseInt(h) * 3600;
    	  int minutos = Integer.parseInt(m) * 60;
    	  int segundos = Integer.parseInt(s);

    	  //somo tudo os resultados em segundos
    	  resultSegundos = (long) (hora + minutos + segundos);

    	  //retorno do resultado
    	  return resultSegundos;
    }
    
    /**
     * @param segundos
     * @return
     */
    public static String segundosToHoras(Long segundos){     
    	int minutos = segundos.intValue() / 60;   
    	int minuto = minutos % 60;   
    	int hora = minutos / 60;   
    	String hms = String.format ("%02d:%02d", hora, minuto);  
    	
    	return hms;
    }
}
