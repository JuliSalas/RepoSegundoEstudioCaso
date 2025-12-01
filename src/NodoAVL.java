package src;

public class NodoAVL {

    private int llave;
    private int altura;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;

    public NodoAVL(int llave) {
        this.llave = llave;
        this.altura = 1;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    //Getters
    public int getLlave() {
        return llave;
    }

    public int getAltura() {
        return altura;
    }

    public NodoAVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoAVL getHijoDerecho() {
        return hijoDerecho;
    }

    //Setters
    public void setLlave(int llave) {
        this.llave = llave;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    // Evalúa la altura de un nodo
    public int evaluarAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    // Evalúa el factor de balance
    public int evaluarFactorBalance(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return evaluarAltura(nodo.getHijoIzquierdo()) - evaluarAltura(nodo.getHijoDerecho());
    }


    //ROTACIONES AVL


    public NodoAVL rotarDerecha(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.getHijoIzquierdo();
        NodoAVL temp = nuevoPadre.getHijoDerecho();

        //Realizar rotación
        nuevoPadre.setHijoDerecho(nodo);
        nodo.setHijoIzquierdo(temp);

        //Actualizar alturas
        nodo.setAltura(1 + Math.max(evaluarAltura(nodo.getHijoIzquierdo()),
                                    evaluarAltura(nodo.getHijoDerecho())));
        nuevoPadre.setAltura(1 + Math.max(evaluarAltura(nuevoPadre.getHijoIzquierdo()),
                                          evaluarAltura(nuevoPadre.getHijoDerecho())));

        return nuevoPadre;
    }

    public NodoAVL rotarIzquierda(NodoAVL nodo) {
        NodoAVL nuevoPadre = nodo.getHijoDerecho();
        NodoAVL temp = nuevoPadre.getHijoIzquierdo();

        //Realizar rotación
        nuevoPadre.setHijoIzquierdo(nodo);
        nodo.setHijoDerecho(temp);

        //Actualizar alturas
        nodo.setAltura(1 + Math.max(evaluarAltura(nodo.getHijoIzquierdo()),
                                    evaluarAltura(nodo.getHijoDerecho())));
        nuevoPadre.setAltura(1 + Math.max(evaluarAltura(nuevoPadre.getHijoIzquierdo()),
                                          evaluarAltura(nuevoPadre.getHijoDerecho())));

        return nuevoPadre;
    }

    public NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
        return rotarDerecha(nodo);
    }

    public NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
        return rotarIzquierda(nodo);
    }
}
