package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AVL arbol = new AVL();
        int opcion = 0;

        do {
            System.out.println("\n===== MENÚ AVL =====");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Eliminar nodo");
            System.out.println("4. Mostrar árbol");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Digite la llave a insertar: ");
                    int llaveIns = sc.nextInt();
                    arbol.insertar(llaveIns);
                    break;

                case 2:
                    System.out.print("Digite la llave a buscar: ");
                    int llaveBus = sc.nextInt();
                    NodoAVL resultado = arbol.buscar(llaveBus);
                    if (resultado != null) {
                        System.out.println("Nodo encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite la llave a eliminar: ");
                    int llaveDel = sc.nextInt();
                    arbol.eliminar(llaveDel);
                    break;

                case 4:
                    System.out.println("Recorrido InOrden:");
                    arbol.inOrden();
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        sc.close();
    }
}
