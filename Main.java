import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        Utils.loadWordsFromFile("sample.txt", bst);
        System.out.println("Palavras carregadas do arquivo. Total: " + bst.size());

        while (true) {
            System.out.println("\n1. Buscar palavra");
            System.out.println("2. Buscar por substring");
            System.out.println("3. Adicionar palavra");
            System.out.println("4. Remover palavra");
            System.out.println("5. Mostrar total de palavras");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            switch (option) {
                case 1:
                    System.out.print("Palavra a buscar: ");
                    String word = Utils.normalizeWord(scanner.nextLine());
                    long start = System.nanoTime();
                    boolean found = bst.contains(word);
                    long end = System.nanoTime();
                    System.out.println(found ? "Palavra encontrada!" : "Palavra não encontrada.");
                    System.out.println("Tempo de busca: " + (end - start) + " ns");
                    break;
                case 2:
                    System.out.print("Substring: ");
                    String sub = Utils.normalizeWord(scanner.nextLine());
                    System.out.println("Resultados: " + bst.searchBySubstring(sub));
                    break;
                case 3:
                    System.out.print("Nova palavra: ");
                    String newWord = Utils.normalizeWord(scanner.nextLine());
                    bst.insert(newWord);
                    System.out.println("Palavra adicionada.");
                    break;
                case 4:
                    System.out.print("Palavra a remover: ");
                    String toRemove = Utils.normalizeWord(scanner.nextLine());
                    if (bst.remove(toRemove))
                        System.out.println("Palavra removida.");
                    else
                        System.out.println("Palavra não encontrada.");
                    break;
                case 5:
                    System.out.println("Total de palavras: " + bst.size());
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
