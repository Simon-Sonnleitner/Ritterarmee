import java.util.Random;
public class AUFMARSCH extends SPIEL
{
    int zaehler = 0;
    RITTER[] armee;

    int freePos;

    public AUFMARSCH(){
        super(960,540);
        zaehler++;
        this.armee = new RITTER[8];
        super.starteTickerNeu(1);
        super.setzeHintergrundgrafik("wiese.jpg");
    }

    public void abmarschieren(){
        /*
          Der vorderste Ritter marschiert ab
         */
        armee[7].animiereGerade(0.75, 20, -4, false);
        System.out.println("Ritter "+ armee[7].name + " marschiert ab");
    }

    public void aufruecken(){
        for (int i = 7; i > 0 ; i--) {
            /*
              Die ritter rücken auf, indem der höhere Index des Arrays durch den um eins niedrigeren Index ersetzt wird. Bsp.: Index 7 (armee[7]) wird durch Index 6 (armee[6]) ersetzt
             */
            this.armee[i] = armee[i-1];
            if (armee[i-1] == null) continue;
            armee[i-1].animiereGerade(0.75, -14 + (i *4), -4, false);
            System.out.println("Ritter " + armee[i-1].name + " rückt auf Position " + freePos);
        }
        this.armee[0] = null;
    }

    public void einordnen(RITTER ritter){
        /*
          Setzt den Mittelpunkt außerhalb des Spielfensters, damit die animation schön aussieht
         */
        ritter.setzeMittelpunkt(-20, -4);
        ritter.animiereGerade(0.75, -14 + (freePos*4), -4, false);
        System.out.println("Ritter "+ ritter.name + " marschiert auf Position " + freePos);
    }

    public int freiePositionFinden(){
        for (int i = 0; i < armee.length; i++) {
            if (armee[i] == null) continue;
            return i - 1;
        }
            return 7;
    }

    @Override
    public void tick(){
        String nBild = "";
        Random rand = new Random();
        int zufallszahl = rand.nextInt(4);
        switch (zufallszahl) {
            case 0 -> nBild = "ritter_1.png";
            case 1 -> nBild = "ritter_2.png";
            case 2 -> nBild = "ritter_3.png";
            case 3 -> nBild = "ritter_4.png";
        }
        String nName = "Olaf"+zaehler;
        this.freePos = freiePositionFinden();
        if (freePos > -1) {
            armee[freePos] = new RITTER(-3, -4, nBild, nName);
            einordnen(armee[freePos]);
        }
        if (zufallszahl == 1) {
            abmarschieren();
            aufruecken();
        }
        zaehler++;
    }
}
