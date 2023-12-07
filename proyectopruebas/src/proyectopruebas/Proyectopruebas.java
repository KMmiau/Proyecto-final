
package proyectopruebas;

import java.util.Scanner;

public class Proyectopruebas {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //HP's y estadisticas--------------------------------------
        int playerhp, def, defHueso, defFlex, monhp, monmax, maxhp, aumentosM, aumentosF;
        int fuerza, magia, potemagia, reset;
        int dañoRecibido;
        monhp = 0; dañoRecibido = 0; potemagia = 1; monmax = 0; defHueso = 0; reset = 1; defFlex = 0;
        playerhp = 100; maxhp = 100; fuerza = 1; magia = 0; def = 0; aumentosM = 0; aumentosF = 0;
        //movimientos y usar movimientos---------------------------
        boolean golpe, fuego, tacleada, golpe_sorpresa, flexion, meditar, tumbaHielo, exploSangre, invocar, barreraHueso;
        int mon, eleccion, curacion;
        int invocado = 0;
        //monstruos------------------------------------------------
        String nombreMon = "Placeholder";
        int monxp = 0;
        int monF = 0;
        //comprobantes booleanos
        boolean necro = true; boolean maquina = true;
        boolean monZombie = false;
        boolean monColiseo = false;
        boolean seleccion = false;
        golpe = false; fuego = false; tacleada = false; meditar = false; tumbaHielo = false; exploSangre = false; invocar = false; barreraHueso = false;
        golpe_sorpresa = false; flexion = false; golpe = true;
        //contadores y escalado
        int combatesFin = 0;
        int verescalado; 
        int Sverescalado;
        int escalado = 1;
        int Sesclado = 1;
        int puntaje;
        int exp = 0;
        int lvl = 1;
        int expreq = 5;
        //evento ( _ significa que es el texto para las opciones)
        String[][] evento = new String[5][3];
        evento[0][0] = "Pisas una trampa de la masmorra y una roca gigante gira hacía ti! que deberias hacer?"; //trampa 20%
        evento[0][1] = "1) atacar la roca y recibir el golpe(25% de tu vida maxima como daño)";
        evento[0][2] = "2) saltar fuera del camino(-20% de vida maxima)";
        evento[1][0] = "Encuentras un cuarto con varias cajas con cosas utiles, elige una";//cajas 30%
        evento[1][1] = "1) Comida(recupera 25% de vida)";
        evento[1][2] = "2) nuevas armas(+10% de fuerza y magia)";
        evento[2][0] = "Despues de recibir un golpe que te dejó inconsiente te levantas en lo que parece un coliseo";//coliseo 10%
        evento[2][1] = "1) pelear(el siguiente combate será uno especial, que dará 1 nivel inmediatamente y aumentará tus estadisticas)";
        evento[2][2] = "2) huir(escapa de el coliseo, los combates seran normales, pero no hay recompensas)";
        evento[3][0] = "Encuentras un cementerio en la mazmorra, en una mesa hay un libro de artes oscuras llamado 'Necronomicon'";//necromancia 10%
        evento[3][1] = "1) leerlo(consigue dos habilidades especiales, el siguiente combate sera contra zombies poderosos)";
        evento[3][2] = "2) ignorar el libro";
        evento[4][0] = "Encuentras una extraña maquina en la mazmorra, que haces?";//reset 10%
        evento[4][1] = "1) usarla (pierde todos tus movimientos aprendidos, multiplica tus estadisticas por 2 por cada uno de los movimientos olvidados)";
        evento[4][2] = "2) ignorarla";
        //20% de nada, 30% cuando necromancia ya pasó
        int eventoRan = 0;
        //Empieza el juego!
        System.out.println("Bienvenido a Inifinidungeons, presiona cualquier letra para empezar.");
        String start = input.nextLine();
        do{
        if(playerhp > maxhp)
            playerhp = maxhp;
         mon = (int)(Math.random() * 100);//--elegir enemigos
         if(monZombie == true){
             monhp = (60)*(escalado)*(Sesclado);
             monmax = (60)*(escalado)*(Sesclado);
             monF = ((15 +(combatesFin))*(escalado))/2;
             monxp = 0;
             nombreMon = "Zombie";
             monZombie = false;
         }
         else if(monColiseo == true){
             monhp = (40)*(escalado)*(Sesclado);
             monmax = (40)*(escalado)*(Sesclado);
             monF = ((20 + (combatesFin))*(escalado))/2;
             monxp = expreq;
             nombreMon = "ciclope";
             monColiseo = false;
         }
         else if(mon <= 40){
              monhp = ((20 + combatesFin)*(escalado)*(Sesclado))/2;
              monmax = ((20 + combatesFin)*(escalado)*(Sesclado))/2;
              monF = ((combatesFin + 4) * (escalado))/2;
              monxp = (3 + combatesFin);
              nombreMon = "Duende";
        }
         else if(mon < 90 && mon > 40){
             monhp = (20 + combatesFin)*(escalado)*(Sesclado);
             monmax = (20 + combatesFin)*(escalado)*(Sesclado);
             monF = (escalado * 4);
             monxp = (4 + combatesFin);
             nombreMon = "Fantasma";
         }
         else if(mon >= 90 && combatesFin > 5){
             monhp = (40)*(escalado)*(Sesclado);
             monmax = (40)*(escalado)*(Sesclado);
             monF = 10*(escalado);
             monxp = (5 * escalado);
             nombreMon = "enemigo raro! Golem";
         
         }
         else{
              monhp = ((20 + combatesFin)*(escalado)*(Sesclado))/2;
              monmax = ((20 + combatesFin)*(escalado)*(Sesclado))/2;
              monF = ((combatesFin + 4) * (escalado))/2;
              monxp = (3 + combatesFin);
              nombreMon = "Duende";
         
         }//--------------------------------------------------
        
          do{//Menu para mostrar habilidades y vida restante
MostrarCombate(lvl, playerhp, maxhp, monhp, monmax, nombreMon);

            do{
                seleccion = false;
MostrarHabilidades(golpe, tacleada, golpe_sorpresa, flexion, barreraHueso, fuego, meditar, exploSangre, tumbaHielo, invocar);

            eleccion = input.nextInt();
            System.out.println("-----------------------------------------");
            
            switch(eleccion){//--seleccion de movimiento
                case(1):
                    if(golpe == true){
                    dañoRecibido = 4 + fuerza;
                    monhp -= (dañoRecibido);
                    seleccion = true;
                        System.out.println("Golpeas al enemigo! " + dañoRecibido + " de daño!");
                }
                    if(golpe == false)
                        System.out.println("no conoces este ataque, no haces nada");
                    break;     
                case(2):
                    if(tacleada == true){
                    dañoRecibido = 1 + (fuerza * 3);
                    monhp -= dañoRecibido;
                    seleccion = true;
                        System.out.println("tacleas al enemigo! " + dañoRecibido + " de daño!");
                }
                    if(tacleada == false)
                        System.out.println("no conoces este ataque, no haces nada");
                    break;
                case(3):
                    if(fuego == true){
                    dañoRecibido = 3 + ((magia + potemagia) * 2);
                    monhp -= dañoRecibido;
                    potemagia = 0;
                    seleccion = true;
                        System.out.println("tiras una bola de fuego! " + dañoRecibido + " de daño!");
                    }
                    if(fuego == false)
                        System.out.println("no conoces este ataque, no haces nada");
                    break;
                case(4):
                    if(golpe_sorpresa == true){
                    dañoRecibido = monF * 3;
                    monhp -= dañoRecibido;
                    seleccion = true;
                        System.out.println("Ataque sorpresa! " + dañoRecibido + " de daño!");
                    }
                    if(golpe_sorpresa == false)
                        System.out.println("no conoces este ataque, no haces nada");
                    break;
                case(5):
                    if(flexion == true && defFlex == 0){
                    def += 2 * lvl;
                    defFlex = 1;
                    seleccion = true;
                        System.out.println("flexionas! + " + def + " de defensa!");
                    }
                    else if(def > 0){
                        System.out.println("ya lo usaste este combate, no haces nada");
                    }
                    if(flexion == false){
                        System.out.println("no conoces este movimiento, no haces nada");
                    }
                  break;
                case(6):
                    if(meditar == true){
                    potemagia += magia + (lvl * 2);
                    seleccion = true;
                        System.out.println("Meditas... +" + potemagia + " de magia acumulada!");
                    }
                    if(meditar == false)
                        System.out.println("No conoces este movimiento, no haces nada");
                    break;
                case(7):
                    if(exploSangre == true){
                    if((monmax / 4) >= monhp){
                        monhp = 0;
                        System.out.println("Explosion! enemigo rematado!");
                        potemagia = 0;
                        }
                    else{
                            System.out.println("no funciono...");
                        }
                    seleccion = true;
                    }
                    if(exploSangre == false){
                        System.out.println("no conoces este ataque, no haces nada");
                    }
                    break;
                case(8):
                    if(tumbaHielo == true){
                        dañoRecibido = (magia + (potemagia * 3))*2;
                        monhp -= dañoRecibido;
                        
                        seleccion = true;
                        if(potemagia > 0){
                        System.out.println("Hay demasiada nieve, no puedes ver nada... " + dañoRecibido + " de daño");
                        potemagia = 0;}
                        else{
                            System.out.println("Entierras al enemigo en nieve, " + dañoRecibido + " de daño");}
                    }
                    if(tumbaHielo == false){
                        System.out.println("No conoces este ataque, no haces nada");
                    potemagia = 0;
                    }
                    break;
                case(9):
                    if(invocar == true){
                        seleccion = true;
                        invocado += magia * 3;
                        potemagia = 0;
                        System.out.println("Esqueletos aparecen a ayudarte! el daño total por turno es " + invocado);
                    }
                    if(invocar == false)
                        System.out.println("No conoces este movimiento, no haces nada");
                    break;
                case(10):
                    if(barreraHueso == true && defHueso == 0){
                        def += 2 * lvl;
                        defHueso = 1;
                        seleccion = true;
                        System.out.println("creas una barrera! + " + def + " de defensa!");
                    }
                    else if(defHueso > 0){
                        System.out.println("ya lo usaste este combate, no haces nada");
                    }
                    if(barreraHueso == false)
                        System.out.println("No conoces este movimiento, no haces nada");
                    break;
                default: seleccion = false;
                    System.out.println("numero de movimiento no valido, no haces nada");
                    
            }//
                if(invocado > 0){
                    monhp -= invocado;
                    System.out.println("Tus esqueletos atacan! " + invocado + " de daño");
                }
                dañoRecibido = 0;
                if(monhp > 0){
                dañoRecibido = monF - def;
                if(dañoRecibido < 0)
                    dañoRecibido = 1;
                System.out.println(nombreMon + " te ataca! " + dañoRecibido + " de daño!");
                playerhp -= dañoRecibido;
                }
            }while(seleccion == false);//do while para evitar selecciones invalidas en el combate
            if(monhp < 0)
            monhp = 0;
            
              
          }while((monhp > 0) && (playerhp > 0)); // fin del combate si alguien tiene 0 hp
          def = 0; invocado = 0; defHueso = 0; defFlex = 0;
        //resultados de combate +experiencia
          System.out.print("Combate terminado! ");
          if(playerhp >0){
              curacion = (combatesFin * 2);
              if(curacion == 0)
                  curacion = 1;
              playerhp += curacion;
              System.out.println("Te curas " + curacion + " de vida");
          if(playerhp > maxhp)
            playerhp = maxhp;
          curacion = 0;
          exp += (monxp * escalado);
          
          combatesFin++;//escalado de mounstros
          verescalado = combatesFin % 5;
          Sverescalado = combatesFin % 10;
          if(verescalado == 0)
              escalado++;
          if(Sverescalado == 0)
              Sesclado *= 2;
          if(exp > expreq){
              lvl++;
              expreq *= (5 * escalado);
              exp = 0;
              maxhp += (combatesFin)+(maxhp / 4);
              System.out.println("subes de nivel!");
              do{
              //seleccion de estadistica y habilidades
                  System.out.println("selecciona que estadistica quieres mejorar! tambien aprenderas una habilidad de la estadistica mejorada");
                  System.out.println("1 para fuerza, 2 para magia");
                  eleccion = input.nextInt();
                  switch(eleccion){
                      case(1):
                          fuerza += lvl;
                          if(golpe_sorpresa == true && flexion == false){
                              flexion = true;
                              System.out.println("Flexion aprendida, aumenta tu defensa por el resto del combate! una sola vez por combate");
                          }
                          if(tacleada == true && golpe_sorpresa == false){
                              golpe_sorpresa = true;
                              System.out.println("golpe sorpresa aprendido, ataca con la fuerza de tu enemigo!");
                          }
                          if(golpe == true && tacleada == false){
                              tacleada = true;
                              System.out.println("tacleada aprendida, hace el triple de tu fuerza como daño!");
                          }
                          seleccion = true;
                          break;
                      case(2):
                          magia += lvl;
                          if(exploSangre == true && tumbaHielo == false){
                              tumbaHielo = true;
                              System.out.println("Tumba de hielo aprendida... un ataque letal que a primeras no parece mucho mejor que el fuego, prueba a meditar");
                          }
                          if(meditar == true && exploSangre == false){
                              exploSangre = true;
                              System.out.println("Explosion de sangre aprendida, este ataque mata al enemigo si tiene menos del 25% de vida");
                          }
                          if(fuego == true && meditar == false){
                              meditar = true;
                              System.out.println("Meditacion aprendida, tu siguiente ataque magico gana daño adicional! (funciona mejor con ataques de hielo)");
                          }
                          if(fuego == false){
                              fuego = true;
                              System.out.println("Bola de fuego aprendida, lanza al enemigo una bola de fuego que hace 2 veces tu magia +3 como daño!");
                          }
                          break;
                          default: seleccion = false;
                              System.out.println("seleccion no valida, intenta de nuevo");
                  }
              }while(seleccion == false);//do while para evitar selecciones invalidas en la seleccion de estadisticas
              seleccion = false;
          }
          System.out.println("-----------------------------------------");
          
          }
          //eventos
          if(playerhp > 0){//hacer un evento si el jugador tiene mas de 0 hp, para evitar curarse estando muerto
          eventoRan = (int)(Math.random() * 100);
          do{
          if(eventoRan <= 99 && eventoRan >= 80){//evento 1: trampa 20% de aparecer
          System.out.println(evento[0][0]);
            System.out.println(evento[0][1]);
            System.out.println(evento[0][2]);
            eleccion = input.nextInt();
            switch(eleccion){
                case(1):
                    seleccion = true;
                    dañoRecibido = (maxhp / 4);
                    playerhp -= dañoRecibido;
                    System.out.println("Con todo tu poder intentas destuir la roca, aun asi recibes " + dañoRecibido + " de daño");
                    break;
                case(2):
                    seleccion = true;
                    dañoRecibido = (maxhp / 5);
                    maxhp -= dañoRecibido;
                    System.out.println("Saltas fuera de la trayectoria de la roca, parece que te torciste un tobillo (-" + dañoRecibido + " de vida maxima)");
                    break;
                default: seleccion = false;
                    System.out.println("seleccion no valida, intenta de nuevo");
            }
          }
          else if(eventoRan < 80 && eventoRan >= 49){//evento 2: cajas utiles 30% de aparecer
                  System.out.println(evento[1][0]);
                  System.out.println(evento[1][1]);
                  System.out.println(evento[1][2]);
                  eleccion = input.nextInt();
            switch(eleccion){
                case(1):
                    seleccion = true;
                    curacion = (maxhp / 4);
                    playerhp += curacion;
                    if(playerhp > maxhp)
                        playerhp = maxhp;
                    System.out.println("Despues de comer te sientes mejor, te curas " + curacion + " de vida");
                    break;
                case(2):
                    seleccion = true;
                    aumentosF = (fuerza / 10); aumentosM = (magia / 10);
                    if(aumentosF == 0)
                        aumentosF = 1;
                    if(aumentosM == 0)
                        aumentosM = 1;
                    magia += aumentosM; fuerza += aumentosF;
                    System.out.println("Te llevas un par de armas de la caja (tu magia y fuerza aumentan en 10%)");
                    aumentosM = 0; aumentosF = 0;
                    break;
                default: seleccion = false;
                    System.out.println("seleccion no valida, intenta de nuevo");
            }
          }
          else if(eventoRan < 49 && eventoRan >= 38 && combatesFin > 5){//evento: coliseo 10% de aparecer
              System.out.println(evento[2][0]);
              System.out.println(evento[2][1]);
              System.out.println(evento[2][2]);
              eleccion = input.nextInt();
            switch(eleccion){
                case(1):
                    seleccion = true;
                    aumentosF = (fuerza / 5); aumentosM = (magia / 5);
                    magia += aumentosM; fuerza += aumentosF;
                    System.out.println("Te llenas de valentia al ver a tu oponente (fuerza y magia aumentada en 20%)");
                    monColiseo = true;
                    aumentosM = 0; aumentosF = 0;
                    break;
                case(2):
                    seleccion = true;
                    System.out.println("Escapas de ese lugar antes de ser herido");
                    break;
                default: seleccion = false;
                    System.out.println("seleccion no valida, intenta de nuevo");
            }
          }
          else if(eventoRan < 38 && eventoRan >= 27 && necro == true && combatesFin > 6){//evento: necromancia 10%
              System.out.println(evento[3][0]);
              System.out.println(evento[3][1]);
              System.out.println(evento[3][2]);
              eleccion = input.nextInt();
            switch(eleccion){
                case(1):
                    seleccion = true;
                    monZombie = true; necro = false;
                    invocar = true; barreraHueso = true;
                    System.out.println("Aprendes invocar esqueltos! tus esqueletos hacen daño basado en tu magia por turno, mientras mas invoques, mas daño, no afectado por meditar");
                    System.out.println("Aprendes barrera de huesos! gana defensa basado en tu nivel, solo se puede usar 1 vez por combate");
                    System.out.println("Despues de leer el libro, vez un zombie levantarse de una tumba!");
                    break;
                case(2):
                    seleccion = true;
                    System.out.println("Ignoras el libro");
                    break;
                default: seleccion = false;
                    System.out.println("seleccion no valida, intenta de nuevo");
            }
          }
          else if(eventoRan < 27 && eventoRan >= 16 && maquina == true && combatesFin > 10){//evento: maquina reset 10%
              System.out.println(evento[4][0]);
              System.out.println(evento[4][1]);
              System.out.println(evento[4][2]);
              eleccion = input.nextInt();
              switch(eleccion){
                case(1):
                    seleccion = true;
                    if(tacleada == true){
                        tacleada = false;
                        reset++;}
                    if(golpe_sorpresa == true){
                        golpe_sorpresa = false;
                        reset++;}
                    if(flexion == true){
                        flexion = false;
                        reset++;}
                    if(meditar == true){
                        meditar = false;
                        reset++;}
                    if(exploSangre == true){
                        exploSangre = false;
                        reset++;}
                    if(tumbaHielo == true){
                        tumbaHielo = false;
                        reset++;}
                    if(barreraHueso == true){
                        barreraHueso = false;
                        reset++;}
                    if(invocar == true){
                        invocar = false;
                        reset++;}
                    magia *= (reset / 2); fuerza *= (reset / 2);
                    System.out.println("La maquina absorbe tu conocimiento... Pero sientes un poder creciendo dentro de ti, fuerza y magia multiplicada por " + reset);
                    maquina = false;
                    break;
                case(2):
                    seleccion = true;
                    System.out.println("Ignoras la maquina");
                    break;
                default: seleccion = false;
                    System.out.println("seleccion no valida, intenta de nuevo");
              }
          }
          else{//no evento
                System.out.println("No ocurre ningun evento");
                seleccion = true;
          }
          }while(seleccion == false);//do while para evitar selecciones invalidas en el evento
          seleccion = false;
            System.out.println("-----------------------------------------");
          }
        }while(playerhp > 0);//Fin del juego si el jugador tiene 0 hp
        System.out.println("haz muerto... ");
        puntaje = ((fuerza + magia) * escalado * lvl);
        System.out.println("Tu puntaje es: " + puntaje);
    }
    public static void MostrarCombate(int lvl, int playerhp, int maxhp, int monhp, int monmax, String nombreMon){
            System.out.print("Jugador nivel " + lvl + ":");
            System.out.print("HP: ");
            System.out.println(playerhp + " / " + maxhp);
            System.out.print(nombreMon + " ");
            System.out.print("HP: ");
            System.out.println(monhp + " / " + monmax);
    }
    public static void MostrarHabilidades(boolean golpe, boolean tacleada, boolean golpe_sorpresa, boolean flexion, boolean barreraHueso, boolean fuego, boolean meditar, boolean exploSangre, boolean tumbaHielo, boolean invocar){
                System.out.println("Elige un ataque");
            if(golpe == true)
                System.out.print("golpe(1) - ");
            if(tacleada == true)
                System.out.print("tacleada(2) - ");
            if(golpe_sorpresa == true)
                System.out.print("Golpe sorpresa(4) - ");
            if(flexion == true)
                    System.out.print("Flexion(5) - ");
            if(barreraHueso == true)
                    System.out.print("Barrera de huesos(10) - ");
                System.out.println("");
            if(fuego == true)
                System.out.print("fuego(3) - ");
            if(meditar == true)
                    System.out.print("Meditar(6) - ");
            if(exploSangre == true)
                    System.out.print("Explosion de sangre(7) - ");
            if(tumbaHielo == true)
                    System.out.print("Tumba de hielo(8) - ");
            if(invocar == true)
                    System.out.print("Invocar esqueletos(9) - ");
    }
}
//public static void MostrarHabilidades(golpe, tacleada, golpe_sorpresa, flexion, barreraHueso, fuego, meditar, exploSangre, tumbaHielo, invocar)