package com.houss.sum;

public class LogarithmExample {
    public static void main(String[] args) {
        // Création de tableaux de différentes tailles
        int[] nums1 = new int[8];  // Puissance exacte de 2
        int[] nums2 = new int[9];  // Non-puissance de 2, nécessitant un arrondi
        int[] nums3 = new int[15]; // Autre exemple nécessitant un arrondi

        System.out.println(Math.log(15));
        System.out.println(Math.log(2));
        System.out.println(Math.log(15)/Math.log(2));

        // Calcul et affichage des résultats
        System.out.println("Resultat pour 8 : " + Math.ceil(Math.log(nums1.length) / Math.log(2)));
        System.out.println("Resultat pour 9 : " + Math.ceil(Math.log(nums2.length) / Math.log(2)));
        System.out.println("Resultat pour 15: " + Math.ceil(Math.log(nums3.length) / Math.log(2)));
    }
}

