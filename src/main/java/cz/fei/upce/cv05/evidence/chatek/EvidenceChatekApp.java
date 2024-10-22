package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
    final static int KONEC_PROGRAMU = 0;
    final static int VYPIS_CHATEK = 1;
    final static int VYPIS_KONKRETNI_CHATKU = 2;
    final static int PRIDANI_NAVSTEVNIKU = 3;
    final static int ODEBRANI_NAVSTEVNIKU = 4;
    final static int CELKOVA_OBSAZENOST = 5;
    final static int VYPIS_PRAZDNYCH_CHATEK = 6;

    final static int VELIKOST_KEMPU = 10;
    final static int MAX_VELIKOST_CHATKY = 5;

    static Scanner scanner = new Scanner(System.in);

    // Definovani pole podle velikosti kempu (poctu chatek)
    static int[] chatky = new int[VELIKOST_KEMPU];
    static int operace;

    public static void main(String[] args) {

        do {
            System.out.println("""
                               
                    MENU:
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> {
                    vypisChatky();
                }

                case VYPIS_KONKRETNI_CHATKU -> {
                    vypisKonkretniChatku();
                }

                case PRIDANI_NAVSTEVNIKU -> {
                    pridejNavstevniky();
                }

                case ODEBRANI_NAVSTEVNIKU -> {
                    odeberNavstevniky();
                }

                //celkovy pocet navstevniku
                case CELKOVA_OBSAZENOST -> {
                    System.out.println("Celkova obsazenost kempu je: " + zjistiPocetNavstevniku() + " navstevniku");
                }

                case VYPIS_PRAZDNYCH_CHATEK -> {
                    vypisPrazdneChatky();
                }

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }

                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }

    private static void vypisChatky() {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    private static void vypisKonkretniChatku() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1; // -1 kvuli indexu

        if (!kontrolaIndexu(cisloChatky)) {
            return;
        }

        System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
    }

    private static void vypisPrazdneChatky(){
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
            }
        }
    }
    
    private static void pridejNavstevniky() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky o 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;

        if (!kontrolaIndexu(cisloChatky)) {
            return;
        }

        // Ziskani poctu navstevniku, kteri se chteji v chatce ubytovat
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();

        // Zaporne cislo nebo prilis velky nevalidni vstup
        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        // Pokud je pocet uz ubytovanych plus ty co se chteji ubytovat vetsi nez kapacita chatky je to nevalidni vstup
        if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }

        // Pridej nove ubytovane do chatky k tem co uz tam jsou
        chatky[cisloChatky] += pocetNavstevniku;
    }
    
    private static void odeberNavstevniky() {
        // Ziskani cisla chatky od uzivatele
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky o 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;

        if (!kontrolaIndexu(cisloChatky)) {
            return;
        }

        // Ziskani poctu navstevniku, kteri se chteji v chatce ubytovat
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        
        if (pocetNavstevniku < 0 || chatky[cisloChatky] - pocetNavstevniku < 0){
            System.out.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }
        chatky[cisloChatky] -= pocetNavstevniku;
    }
    
    private static int zjistiPocetNavstevniku() {
        int pocet = 0;
        for (int i = 0; i < chatky.length; i++) {
            pocet += chatky[i];
        }
        return pocet;
    }
    
    private static boolean kontrolaIndexu(int index) {
        // Zaporne nebo cislo vetsi nez je pocet chatek je nevalidni vstup
        if (index < 0 || index >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return false;
        }
        return true;
    }
}
