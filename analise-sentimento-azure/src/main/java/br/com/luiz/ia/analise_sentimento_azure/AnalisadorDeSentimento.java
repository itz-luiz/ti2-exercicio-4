package br.com.luiz.ia.analise_sentimento_azure;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.DocumentSentiment;
import com.azure.core.credential.AzureKeyCredential;

public class AnalisadorDeSentimento {

    // Ambas strings iniciadas em branco, para não vazar a key e o endpoint
    private static final String AZURE_KEY = "";
    private static final String AZURE_ENDPOINT = "";

    public static void main(String[] args) {
        TextAnalyticsClient client = new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(AZURE_KEY))
                .endpoint(AZURE_ENDPOINT)
                .buildClient();

        String textoParaAnalisar = "Eu tive uma experiência maravilhosa neste restaurante! A comida estava deliciosa e o atendimento foi impecável. Recomendo a todos!";
               
        DocumentSentiment documentSentiment = client.analyzeSentiment(textoParaAnalisar, "pt-br");

        System.out.println("= Resultado da Análise =");
        System.out.printf("Sentimento Geral: %s%n", documentSentiment.getSentiment());

        System.out.println("Pontuações de Confiança:");
        System.out.printf("Positivo: %.2f%n", documentSentiment.getConfidenceScores().getPositive());
        System.out.printf("Neutro:   %.2f%n", documentSentiment.getConfidenceScores().getNeutral());
        System.out.printf("Negativo: %.2f%n", documentSentiment.getConfidenceScores().getNegative());
    }
}