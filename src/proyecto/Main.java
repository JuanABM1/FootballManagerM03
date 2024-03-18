package proyecto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        ArrayList<Persona> personajes = new ArrayList<>();
        ArrayList<Equipo> equipos = new ArrayList<>();
        String[] generalOptions = generalOptions();
        String[] teamOptions = teamOptions();
        int option, indexTeam;
        createDefaultObjects(equipos, personajes);

        boolean exit = false;
        do {
            System.out.println("So far, " + personajes.size() + " persons have been created");
            option = ToolsBrito.menu(generalOptions, "Welcome to politecnics Football manager:");
            switch (option){
                case 1:
                    indexTeam = comprobarSiExisteElEquipo(equipos);
                    manageTeam(indexTeam, teamOptions, equipos, personajes);
                    break;
                case 2:
                    pedirDatosEquipo(equipos);
                    break;
                case 3:
                    darDeAltaPersona(personajes);
                    break;
                case 4:
                    consultarEquipo(equipos);
                    break;
                case 5:
                    consultarPersona(personajes);
                    break;
                case 6:
                    nuevaLiga(equipos);
                    break;
                case 7:
                    realizarEntrenamiento(personajes);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        }while (!exit);
    }

    private static void createDefaultObjects(ArrayList<Equipo> equipos, ArrayList<Persona> personajes) {
        // creacion de equipos con su entrenador y jugadores

        //equipo1
        Equipo e1 = new Equipo("FC Barcelona", 1899, "Barcelona");
        LocalDate l4 = LocalDate.of(1987, 2, 2);
        Jugador j3 = new Jugador("Gerard", "Pique", l4, 5000, Jugador.positions.DEF, 3);
        LocalDate l5 = LocalDate.of(1980, 1, 25);
        Entrenador entrenador2 = new Entrenador("Xavi", "Hernandez", l5, 5000, 2, false);
        e1.signCoach(entrenador2);
        e1.signPlayer(j3);

        // equipo2
        Equipo e2 = new Equipo("Real Madrid", 1902, "Madrid");
        LocalDate l6 = LocalDate.of(2000, 12, 7);
        Jugador j4 = new Jugador("Jose Junior", "Vinicious", l5, 5000, Jugador.positions.DEL, 20);
        LocalDate l7 = LocalDate.of(1959, 6, 10);
        Entrenador entrenador3 = new Entrenador("Carlo", "Ancelotti", l6, 5000, 4, false);
        e2.signCoach(entrenador3);
        e2.signPlayer(j4);

        // añade los equipos al arraylist
        equipos.add(e1);
        equipos.add(e2);

        //crear liga inicial
        Liga liga = new Liga("Politecnics league", 2);
        liga.addTeam(e1);
        liga.addTeam(e2);

        //Creacion de Entrenador libre
        LocalDate l1 = LocalDate.of(1972, 6, 23);
        Entrenador entrenador = new Entrenador("Zinedine", "zidane", l1, 6000, 6, true);
        personajes.add(entrenador);

        // Creacion de jugadores libres
        LocalDate l2 = LocalDate.of(1987, 6, 24);
        LocalDate l3 = LocalDate.of(1985, 2, 5);

        Jugador j1 = new Jugador("Leonel", "Messi", l2, 5000, Jugador.positions.DEL, 10);
        Jugador j2 = new Jugador("Cristiano", "Ronaldo", l3, 5000, Jugador.positions.DEL, 7);

        personajes.add(j1);
        personajes.add(j2);
        
    }

    private static void manageTeam(int indexTeam, String[] teamOptions, ArrayList<Equipo> equipos, ArrayList<Persona> personajes) {
        int option;
        boolean exit = false;

        if (indexTeam != -1){
            do {
                option = ToolsBrito.menu(teamOptions, "Team manager");
                switch (option) {
                    case 1:
                        if (ToolsBrito.pedirConfirmacion("delete your team")) {
                            equipos.remove(indexTeam);
                            exit = true;
                        }
                        break;
                    case 2:
                        askNewName(indexTeam, equipos); //to modify president
                        break;
                    case 3:
                        if (ToolsBrito.pedirConfirmacion("dismiss your trainer")) {
                            personajes.add(equipos.get(indexTeam).getEntrenador()); // saca el entrenador del equipo con el que se esta trabajando

                            equipos.get(indexTeam).dismissCoach();
                        }
                        break;
                    case 4:
                        askToSignTrainerOrPlayer(personajes, indexTeam, equipos);
                        break;
                    case 5:
                        transferPlayer(equipos, personajes, indexTeam);
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            } while (!exit);
        }

    }

    private static void transferPlayer(ArrayList<Equipo> equipos, ArrayList<Persona> personajes, int indexTeam) {
        int idPlayerToTransfer, indexPlayer = 0, indexTeamToTransfer = 0;
        String nameTeamToTransfer, playerName = null, teamName = null;
        boolean validData;
        char confirmation;
        for (int i = 0; i < equipos.get(indexTeam).getJugadores().size(); i++) {
            System.out.println(equipos.get(indexTeam).getJugadores().get(i).getId() + " - "+
                    equipos.get(indexTeam).getJugadores().get(i).getName() + ", "
                    + equipos.get(indexTeam).getJugadores().get(i).getSurname());
        }
        validData = false;
        do {
            idPlayerToTransfer = ToolsBrito.leerInt();
            for (int i = 0; i < equipos.get(indexTeam).getJugadores().size(); i++) {
                if (equipos.get(indexTeam).getJugadores().get(i).getId() == idPlayerToTransfer){
                    validData = true;
                    playerName = equipos.get(indexTeam).getJugadores().get(i).getName();
                    indexPlayer = i;
                }
            }
            if (!validData){
                System.out.println("No player with that id has been found try another one");
            }
        }while (!validData);



        for (int i = 0; i < equipos.size(); i++) {
            System.out.println(equipos.get(i).getName());
        }

        validData = false;
        do {
            nameTeamToTransfer = ToolsBrito.leerString();
            for (int i = 0; i < equipos.get(indexTeam).getJugadores().size(); i++) {
                if (equipos.get(i).getName().equalsIgnoreCase(nameTeamToTransfer)){
                    validData = true;
                    teamName = equipos.get(i).getName();
                    indexTeamToTransfer = i;
                }
            }
            if (!validData){
                System.out.println("No team with that name has been found try another one");
            }
        }while (!validData);

        System.out.println("Okay, are you sure that you want to transfer " + playerName + " to " + teamName + " (Y/N)");
        confirmation = Character.toUpperCase(ToolsBrito.leerChar());
        Jugador j = equipos.get(indexTeam).getJugadores().get(indexPlayer);
        if (j.esTransferible(confirmation)){
            j.transferirAEquipo(equipos.get(indexTeam), equipos.get(indexTeamToTransfer));
        }
    }

    private static void askToSignTrainerOrPlayer(ArrayList<Persona> personajes, int indexTeam, ArrayList<Equipo> equipos) {
        char option;
        int personId, j;
        boolean playerFound;

        // pregunta si quiere fichar jugador o entrenador
        System.out.println("Do u want to sign a trainer or a new player? (T/P)");
        option = Character.toUpperCase(ToolsBrito.leerChar());

        // si ha introducido un valor valido muestra los personajes disponibles
        if (option == 'T' || option == 'P'){
            for (int i = 0; i < personajes.size(); i++) {

                // si el personaje del arraylist es un jugador y se asegura que el usuario quiere ver los jugadores
                if (personajes.get(i) instanceof Jugador && option == 'P'){
                    System.out.println(((Jugador) personajes.get(i)).getId() + " - "+ personajes.get(i).getName() + ", "
                            + personajes.get(i).getSurname());

                // comprueba si es entrenado y que el usuario quiera ver los entrenadores
                } else if (personajes.get(i) instanceof Entrenador && option == 'T') {
                    System.out.println(((Entrenador) personajes.get(i)).getId() + " - " + personajes.get(i).getName() + ", "
                            + personajes.get(i).getSurname());
                }
            }
            // El usuario introduce el nombre del personaje que se va a fichar
            System.out.println("Enter the id of the player you want to sign");
            personId = ToolsBrito.leerInt();

            j = 0;
            playerFound = false;
            while (j < personajes.size() && !playerFound){
                if (personajes.get(j) instanceof Jugador && option == 'P'){
                    if (personId == ((Jugador) personajes.get(j)).getId()){
                        equipos.get(indexTeam).signPlayer((Jugador) personajes.get(j));
                        playerFound = true;
                    }
                } else if (personajes.get(j) instanceof Entrenador && option == 'T') {
                    if (personId == ((Entrenador) personajes.get(j)).getId()) {
                        equipos.get(indexTeam).signCoach((Entrenador) personajes.get(j));
                        playerFound = true;
                    }
                }
                j++;
            }
        }else {
            System.out.println("Error, Next time please choose a valid option");
        }
    }



    private static void askNewName(int indexTeam, ArrayList<Equipo> equipos) {
        String newPresindentName;
        System.out.println("Enter the name of the new president");
        newPresindentName = ToolsBrito.leerString();

        if (equipos.get(indexTeam).getPresidentName().equalsIgnoreCase(newPresindentName)){
            System.out.println("This person is already the president of the team");
        } else if (equipos.get(indexTeam).getPresidentName().isEmpty()) {
            System.out.println("The team does not have a president, so the new one will be assigned");
            equipos.get(indexTeam).setPresidentName(newPresindentName);
        } else {
            System.out.println("The previous one has been fired and now you will have a new one");
            equipos.get(indexTeam).setPresidentName(newPresindentName);
        }

    }

    private static String[] teamOptions() {
        String[] teamOptions = new String[6];
        teamOptions[0] = "0 - exit menu";
        teamOptions[1] = "1 - Termination of team";
        teamOptions[2] = "2 - Modify president";
        teamOptions[3] = "3 - Remove trainer";
        teamOptions[4] = "4 - Sign Player or Trainer";
        teamOptions[5] = "5 - Transfer player";

        return teamOptions;
    }

    private static String[] generalOptions() {
        String[] generalOptions = new String[8];
        generalOptions[0] = "0 - Exit game";
        generalOptions[1] = "1 - Manage team";
        generalOptions[2] = "2 - Register a team";
        generalOptions[3] = "3 - Register a player/trainer";
        generalOptions[4] = "4 - Consult team data";
        generalOptions[5] = "5 - Consult player data";
        generalOptions[6] = "6 - Play new league";
        generalOptions[7] = "7 - Conduct training session";

        return generalOptions;
    }



    private static int comprobarSiExisteElEquipo(ArrayList<Equipo> equipos) {
        String name;
        boolean teamFound = false;
        int indexTeam = 0;

        System.out.println("Enter the name of the team you want to manage");
        name = ToolsBrito.leerString();

        for (int i = 0; i < equipos.size(); i++) {
            if (name.equalsIgnoreCase(equipos.get(i).getName())){ // comprueba que existe un equipo con el nombre introducido
                teamFound = true;
                indexTeam = i;
            }
        }

        if (teamFound){
            return indexTeam;
        }else {
            System.out.println("No Team with this name has been found");
            return -1;
        }
    }

    private static void realizarEntrenamiento(ArrayList<Persona> personajes) {
        boolean hasChanged;
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i) instanceof Jugador){
                ((Jugador) personajes.get(i)).exercise();
                hasChanged = (((Jugador) personajes.get(i)).changePosition());
                if (hasChanged){
                    System.out.println("The player, " + personajes.get(i).getName() + " has changed his position to " + ((Jugador) personajes.get(i)).getPosition());
                }
            }else {
                ((Entrenador) personajes.get(i)).exercise();
                ((Entrenador) personajes.get(i)).raiseSalary();
            }
        }
    }

    private static void nuevaLiga(ArrayList<Equipo> equipos) {
        String nameNewLeague, teamName;
        int numOfTeams, j;
        boolean teamFound = false;

        if (ToolsBrito.pedirConfirmacion("start a new league")){
            // pide el nombre de la liga nueva y la cantidad de equipos que jugaran
            System.out.println("Enter the name of the new league");
            nameNewLeague = ToolsBrito.leerString();

            System.out.println("Enter the number of teams that will participate in the league");
            numOfTeams = ToolsBrito.leerInt();

            if (numOfTeams >= 2 && numOfTeams <= equipos.size()){
                Liga l1 = new Liga(nameNewLeague, numOfTeams);

                // añade los equipos a la liga
                for (int i = 0; i < numOfTeams; i++) {
                    System.out.println("Enter the name of the team that will participate in the league");
                    teamName = ToolsBrito.leerString();

                    // comprueba que existe un equipo con el nombre indicado y lo mete en la arraylist de los equipos que estan en la liga
                    teamFound = false;
                    j = 0;
                    do {
                        if (teamName.equalsIgnoreCase(equipos.get(j).getName())){
                            teamFound = true;
                            l1.addTeam(equipos.get(j));
                        }
                        j++;
                    }while (!teamFound && j < equipos.size());
                }
                if (teamFound){
                    l1.playMatches();
                    System.out.println(l1); // muestra los resultados de la liga
                }else {
                    System.out.println("It seems an error has occurred");
                }



            } else {
                System.out.println("ERROR!!!!\nPlease check that there are enough teams to play this league or the amount of teams for this league is at least 2");
            }
        }
    }

    private static void consultarPersona(ArrayList<Persona> personajes) {
        int i = 0;
        String name;
        boolean nameFound = false;
        System.out.println("Enter the name of the player that you are looking for");
        name = ToolsBrito.leerString();

        do {
            if (name.equalsIgnoreCase(personajes.get(i).getName())){
                System.out.println(personajes.get(i));
                nameFound = true;
            }
            i++;
        }while (!nameFound && i < personajes.size());
        if (!nameFound){
            System.out.println("No player with that name has been found.");
        }
    }

    private static void consultarEquipo(ArrayList<Equipo> equipos) {
        boolean teamFound = false;
        String name;

        System.out.println("Enter the name of the team you are looking for");
        name = ToolsBrito.leerString();

        int i =0;
        do {
            if (equipos.get(i).getName().equalsIgnoreCase(name)){
                System.out.println(equipos.get(i));
                teamFound = true;
            }
            i++;
        }while (i < equipos.size() && !teamFound);
        if (!teamFound){
            System.out.println("No team with that name has been found");
        }
    }

    private static void darDeAltaPersona(ArrayList<Persona> personajes) {
        int salary, chosenPosition, dorsal, tournamentsWon;
        String name, lastName, date;
        LocalDate birthDate = null;
        boolean correctDate, nationalSelection;
        Jugador.positions[] positions = Jugador.positions.values();
        Jugador.positions position = null;
        char typeOfPerson, isNationalTrainer;
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // pide el tipo de personaje que se quiere crear para más adelante
        System.out.println("Do you want to create a Player (P) or a Trainer (T)");
        typeOfPerson = Character.toUpperCase(ToolsBrito.leerChar());    // Lee un char y lo pasa a mayuscula para facilitar la comprobacion

        // pide el nombre
        System.out.println("Enter the name of the person");
        name = ToolsBrito.leerString();

        // pide el apellido
        System.out.println("Enter the person's last name");
        lastName = ToolsBrito.leerString();

        // pide fecha de nacimiento y se asegura que este introducida correctamente
        correctDate = false;
        do {
            System.out.println("Enter the birth date of the person (dd-MM-yyyy)");
            date = ToolsBrito.leerString();
            try {
                birthDate = LocalDate.parse(date,dateTimeFormatter);
                correctDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please introduce the date with the indicated format");
            }
        }while (!correctDate);

        // pregunta el salario
        System.out.println("What salary will have the person?");
        salary = ToolsBrito.leerInt();

        // pregunta la posicion en la que juega el jugador
        if (typeOfPerson == 'P'){
            for (int i = 0; i < positions.length; i++) {
                System.out.println(i + " - " + positions[i]);
            }
            chosenPosition = ToolsBrito.leerInt();
            switch (chosenPosition){
                case 0:
                    position = Jugador.positions.POR;
                    break;
                case 1:
                    position = Jugador.positions.DEF;
                    break;
                case 2:
                    position = Jugador.positions.MIG;
                    break;
                case 3:
                    position = Jugador.positions.DEL;
                    break;
                default:
                    break;
            }

            // pregunta el dorsal del jugador
            System.out.println("Enter the dorsal of the player");
            dorsal = ToolsBrito.leerInt();

            // crea al jugador y lo mete en el arraylsit
            Jugador j1 = new Jugador(name,lastName, birthDate, salary, position, dorsal);
            personajes.add(j1);

        } else if (typeOfPerson == 'T') {
            // pregunta el numero de torneos ganados
            System.out.println("How many tournaments have won this trainer");
            tournamentsWon = ToolsBrito.leerInt();

            // pregunta si ha sido seleccionador nacional
            System.out.println("Has he been chosen as national coach (Y or N)");
            isNationalTrainer = Character.toUpperCase(ToolsBrito.leerChar());
            if (isNationalTrainer == 'Y'){
                nationalSelection = true;
            }else {
                nationalSelection = false;
            }
            // crea el entrenador y lo mete en el arrayList
            Entrenador t1 = new Entrenador(name,lastName,birthDate,salary,tournamentsWon,nationalSelection);
            personajes.add(t1);
        }
    }

    private static void pedirDatosEquipo(ArrayList<Equipo> equipos) { // crear equipo
        String name, cityName, stadiumName, presidentname;
        boolean validName;
        char option;
        int foundationYear;

        // pide el nombre y comprueba si ya existe un equipo con ese nombre
        System.out.println("Enter the team name:");
        name = ToolsBrito.leerString();
        validName = comprobarNombre(equipos, name);
        if (validName){
            // pregunta si quiere que tenga presidente y nombre de estadio para el constructor mas tarde
            System.out.println("Will your team have a president and a stadium? (Y/N)");
            option = Character.toUpperCase(ToolsBrito.leerChar());
            if (option == 'Y' || option == 'N'){

                System.out.println("Enter the foundation year");
                foundationYear = ToolsBrito.leerInt();

                // pregunta el nombre de la ciudad
                System.out.println("Enter the name of city that belongs to the team");
                cityName = ToolsBrito.leerString();

                if (option == 'Y'){
                    System.out.println("Enter the stadium name");
                    stadiumName = ToolsBrito.leerString();

                    System.out.println("Enter the president name");
                    presidentname = ToolsBrito.leerString();

                    Equipo e1 = new Equipo(name, foundationYear, cityName, stadiumName, presidentname);

                    equipos.add(e1);
                }else {
                    Equipo e1 = new Equipo(name, foundationYear, cityName);

                    equipos.add(e1);
                }


            }else {
                System.out.println("An error has occurred next time please choose a valid option");
            }
        }

    }

    private static boolean comprobarNombre(ArrayList<Equipo> equipos, String name) {
        boolean validName = true;
        int i = 0;

        // comprobara si existe un equipo con el nombre indicado
        do {
            if (name.equalsIgnoreCase(equipos.get(i).getName())){
                validName = false;
            }
            i++;
        }while (validName && i != equipos.size());

        return validName;
    }




}