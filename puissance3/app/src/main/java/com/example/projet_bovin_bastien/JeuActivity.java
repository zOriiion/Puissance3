package com.example.projet_bovin_bastien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Deflater;


public class JeuActivity<i> extends AppCompatActivity {

    TextView joueurTextView;
    ImageView[][] tableau; // Appel du tableau visuel
    int[][] tableau2; // Appel du tableau int
    int joueurEnCour = 0; //Variable qui va permettre de savoir qui doit jouer (0 = Joueur 1 et 1 = Joueur 2)
    boolean partiegagne = false;
    int pionposes = 0;
    int k = 0;
    TextView gagnantTextView;
    int nbrJetonColonne1 = 0;
    int nbrJetonColonne2 = 0;
    int nbrJetonColonne3 = 0;
    int nbrJetonColonne4 = 0;
    int nbrJetonColonne5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        Button btn=(Button)findViewById(R.id.button2); btn.setVisibility(View.INVISIBLE);
        TextView affichageGagnant=(TextView)findViewById(R.id.gagnantTextView); affichageGagnant.setVisibility(View.INVISIBLE);
        String stringFromIntentOne = getIntent().getExtras().getString("J1"); //Recupere le nom du joueur 1
        joueurTextView = findViewById(R.id.joueurTextView);
        gagnantTextView = findViewById(R.id.gagnantTextView);
        tableau2 = new int[5][5]; //tableau qui va permettre de connaitre si un pion est posé dans une case et si oui quelle couleur
        for(int j = 0; j<=4; j++){
            for(int i = 0; i <= 4; i++){
                tableau2[j][i] = 0;
            }
        }
        tableau = new ImageView[5][5]; //tableau image qui va permettre de voir la grille
        tableau[0][0] = findViewById(R.id.pion1); //à chaque case du tableau, l'id d'une ImageView différente
        tableau[0][1] = findViewById(R.id.pion2);
        tableau[0][2] = findViewById(R.id.pion3);
        tableau[0][3] = findViewById(R.id.pion4);
        tableau[0][4] = findViewById(R.id.pion5);
        tableau[1][0] = findViewById(R.id.pion6);
        tableau[1][1] = findViewById(R.id.pion7);
        tableau[1][2] = findViewById(R.id.pion8);
        tableau[1][3] = findViewById(R.id.pion9);
        tableau[1][4] = findViewById(R.id.pion10);
        tableau[2][0] = findViewById(R.id.pion11);
        tableau[2][1] = findViewById(R.id.pion12);
        tableau[2][2] = findViewById(R.id.pion13);
        tableau[2][3] = findViewById(R.id.pion14);
        tableau[2][4] = findViewById(R.id.pion15);
        tableau[3][0] = findViewById(R.id.pion16);
        tableau[3][1] = findViewById(R.id.pion17);
        tableau[3][2] = findViewById(R.id.pion18);
        tableau[3][3] = findViewById(R.id.pion19);
        tableau[3][4] = findViewById(R.id.pion20);
        tableau[4][0] = findViewById(R.id.pion21);
        tableau[4][1] = findViewById(R.id.pion22);
        tableau[4][2] = findViewById(R.id.pion23);
        tableau[4][3] = findViewById(R.id.pion24);
        tableau[4][4] = findViewById(R.id.pion25);
        joueurTextView.setText("Au tour de : " + stringFromIntentOne + "(rouge)"); // message qui affiche a qui est le tour
    }

    public void onClick(View v){ //Placer les jetons
        int k = 0; // Variable équivalente au numéro de la colonne
        String stringFromIntentOne = getIntent().getExtras().getString("J1"); //Recupere le nom du joueur 1
        String stringFromIntentTwo = getIntent().getExtras().getString("J2"); //Recupere le nom du joueur 2
        switch (v.getId()){
            case R.id.bouton1: //si on appuis le bouton avec l'id bouton1 alors un pion sera posé sur la 1ere colonne
                k=0;
                nbrJetonColonne1+=1;
                verifiierColonne();
                break;
            case R.id.bouton2:
                k=1;
                nbrJetonColonne2+=1;
                verifiierColonne();
                break;
            case R.id.bouton3:
                k=2;
                nbrJetonColonne3+=1;
                verifiierColonne();
                break;
            case R.id.bouton4:
                k=3;
                nbrJetonColonne4+=1;
                verifiierColonne();
                break;
            case R.id.bouton5:
                k=4;
                nbrJetonColonne5+=1;
                verifiierColonne();
                break;
        }
        for(int j = 4; j>=0; j--)
            if (tableau2[j][k] == 0) { //si il n'y a aucun jeton sur la case choisi
                if(joueurEnCour == 0) { //quel joueur est entrain de jouer
                    tableau[j][k].setImageResource(R.drawable.pionrouge); //remplace l'image blanche par une image rouge
                    joueurEnCour += 1; //donne le tour au joueur suivant
                    tableau2[j][k] = 1; //permet de montrer qu'un pion est place dans le tableau int
                    pionposes++;
                    verifier();
                    break; //met fin a la boucle et evite que toutes les cases se remplissent
                } else { //pareil mais pour le joueur 2
                    tableau[j][k].setImageResource(R.drawable.pionbleu);
                    joueurEnCour -= 1;
                    tableau2[j][k] = 2;
                    pionposes++;
                    verifier();
                    break;
                } //si la case est prise le for va passer a celle du dessus

            }
        if(joueurEnCour == 0){ //affiche a qui est le tour grace aux valeurs recupérés au début de la fonction
            joueurTextView.setText("Au tour de : " + stringFromIntentOne);
            joueurTextView.setTextColor(getResources().getColor(R.color.rouge));
        } else {
            joueurTextView.setText("Au tour de : " + stringFromIntentTwo);
            joueurTextView.setTextColor(getResources().getColor(R.color.bleu));
        }
    }

    private void verifier(){
        String stringFromIntentOne = getIntent().getExtras().getString("J1");
        String stringFromIntentTwo = getIntent().getExtras().getString("J2");
        for(int i=4; i>=0; i--){
            for(int j=2; j>=0; j--) { //Verifier les colonnes
                if (tableau2[j][i] != 0) { //0 equivaut a pas de pions donc on le sort de la verif
                    if (tableau2[j][i] == tableau2[j + 1][i] && tableau2[j][i] == tableau2[j + 2][i]) { // si x pions venant de la colonne 0 est = egal a celui du dessus et egal a celui 2 crans au dessus
                        tableau[j][i].setImageResource(R.drawable.pionvert);
                        tableau[j + 1][i].setImageResource(R.drawable.pionvert);
                        tableau[j + 2][i].setImageResource(R.drawable.pionvert);
                        partiegagne = !partiegagne; // passe la variable partiegagné à 1
                        break;
                    }
                }
            }
        }
        for(int i=2; i>=0; i--){
            for(int j=4; j>=0; j--) { //Verifier les lignes
                if (tableau2[j][i] != 0) {
                    if (tableau2[j][i] == tableau2[j][i + 1] && tableau2[j][i] == tableau2[j][i + 2]) {
                        tableau[j][i].setImageResource(R.drawable.pionvert);
                        tableau[j][i + 1].setImageResource(R.drawable.pionvert);
                        tableau[j][i + 2].setImageResource(R.drawable.pionvert);
                        partiegagne = !partiegagne;
                        break;
                    }
                }
            }
        }
        for(int i=0; i<=2; i++) {
            for (int j = 2; j <= 4; j++) { //Verifier les diagonales de bas gauche à haut droite
                if (tableau2[j][i] != 0) {
                    if (tableau2[j][i] == tableau2[j - 1][i + 1] && tableau2[j][i] == tableau2[j - 2][i + 2]) {
                        tableau[j][i].setImageResource(R.drawable.pionvert);
                        tableau[j - 1][i + 1].setImageResource(R.drawable.pionvert);
                        tableau[j - 2][i + 2].setImageResource(R.drawable.pionvert);
                        partiegagne = !partiegagne;
                        break;
                    }
                }
            }
        }
        for(int i=0; i<=2; i++) {
            for (int j = 0; j <= 2; j++) { //Verifier les diagonales de haut gauche à bas droite
                if (tableau2[j][i] != 0) {
                    if (tableau2[j][i] == tableau2[j + 1][i + 1] && tableau2[j][i] == tableau2[j + 2][i + 2]) {
                        tableau[j][i].setImageResource(R.drawable.pionvert);
                        tableau[j + 1][i + 1].setImageResource(R.drawable.pionvert);
                        tableau[j + 2][i + 2].setImageResource(R.drawable.pionvert);
                        partiegagne = !partiegagne;
                        break;
                    }
                }
            }
        }
        if(partiegagne == true){
            TextView affichageGagnant=(TextView)findViewById(R.id.gagnantTextView); affichageGagnant.setVisibility(View.VISIBLE);
            Button btnFin=(Button)findViewById(R.id.button2); btnFin.setVisibility(View.VISIBLE);
            TextView affichageTour=(TextView)findViewById(R.id.joueurTextView); affichageTour.setVisibility(View.INVISIBLE);
            Button btn1=(Button)findViewById(R.id.bouton1); btn1.setVisibility(View.INVISIBLE);
            Button btn2=(Button)findViewById(R.id.bouton2); btn2.setVisibility(View.INVISIBLE);
            Button btn3=(Button)findViewById(R.id.bouton3); btn3.setVisibility(View.INVISIBLE);
            Button btn4=(Button)findViewById(R.id.bouton4); btn4.setVisibility(View.INVISIBLE);
            Button btn5=(Button)findViewById(R.id.bouton5); btn5.setVisibility(View.INVISIBLE);
            if(joueurEnCour==0){
                gagnantTextView.setText(stringFromIntentTwo + " a gagné !");
            } else {
                gagnantTextView.setText(stringFromIntentOne + " a gagné !");
            }
        }
        if(pionposes>=25){
            Button btnFin=(Button)findViewById(R.id.button2); btnFin.setVisibility(View.VISIBLE);
            TextView affichageGagnant=(TextView)findViewById(R.id.gagnantTextView); affichageGagnant.setVisibility(View.VISIBLE);
            TextView affichageTour=(TextView)findViewById(R.id.joueurTextView); affichageTour.setVisibility(View.INVISIBLE);
            Button btn1=(Button)findViewById(R.id.bouton1); btn1.setVisibility(View.INVISIBLE);
            Button btn2=(Button)findViewById(R.id.bouton2); btn2.setVisibility(View.INVISIBLE);
            Button btn3=(Button)findViewById(R.id.bouton3); btn3.setVisibility(View.INVISIBLE);
            Button btn4=(Button)findViewById(R.id.bouton4); btn4.setVisibility(View.INVISIBLE);
            Button btn5=(Button)findViewById(R.id.bouton5); btn5.setVisibility(View.INVISIBLE);
            gagnantTextView.setText("Plateau plein ! Personne n'a gagné !");
        }

    }

    private void verifiierColonne(){
        if(nbrJetonColonne1 >= 5){
            Button btn1=(Button)findViewById(R.id.bouton1); btn1.setVisibility(View.INVISIBLE);
        }
        if(nbrJetonColonne2 >= 5){
            Button btn2=(Button)findViewById(R.id.bouton2); btn2.setVisibility(View.INVISIBLE);
        }
        if(nbrJetonColonne3 >= 5){
            Button btn3=(Button)findViewById(R.id.bouton3); btn3.setVisibility(View.INVISIBLE);
        }
        if(nbrJetonColonne4 >= 5){
            Button btn4=(Button)findViewById(R.id.bouton4); btn4.setVisibility(View.INVISIBLE);
        }
        if(nbrJetonColonne5 >= 5){
            Button btn5=(Button)findViewById(R.id.bouton5); btn5.setVisibility(View.INVISIBLE);
        }
    }


    public void retourAuMenu(View v){
        this.finish();
    }

}

