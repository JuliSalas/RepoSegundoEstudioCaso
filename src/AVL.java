package src;

    public class AVL {

    private NodoAVL raiz;

    public AVL() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    
    //BUSCAR

    public NodoAVL buscar(int llaveBuscar) {
        if (estaVacio()) {
            System.out.println("El árbol está vacío.\n");
            return null;
        }

        NodoAVL nodoTemp = raiz;

        while (nodoTemp != null && nodoTemp.getLlave() != llaveBuscar) {
            if (llaveBuscar < nodoTemp.getLlave()) {
                nodoTemp = nodoTemp.getHijoIzquierdo();
            } else {
                nodoTemp = nodoTemp.getHijoDerecho();
            }
        }

        if (nodoTemp == null) {
            System.out.println("El nodo buscado no está en el árbol.\n");
            return null;
        }

        return nodoTemp;
    }


    //INSERTAR
    public void insertar(int llave) {
        raiz = insertarNodo(raiz, llave);
    }

    private NodoAVL insertarNodo(NodoAVL nodo, int llave) {
        if (nodo == null) {
            return new NodoAVL(llave);
        }

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(insertarNodo(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(insertarNodo(nodo.getHijoDerecho(), llave));
        } else {
            return nodo;
        }

        // Actualizar altura
        nodo.setAltura(1 + Math.max(nodo.evaluarAltura(nodo.getHijoIzquierdo()),
                                    nodo.evaluarAltura(nodo.getHijoDerecho())));

        // Verificar balance
        int factor = nodo.evaluarFactorBalance(nodo);

        // Casos de rotación
        if (factor > 1 && llave < nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarDerecha(nodo);
        }

        if (factor < -1 && llave > nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarIzquierda(nodo);
        }

        if (factor > 1 && llave > nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarIzquierdaDerecha(nodo);
        }

        if (factor < -1 && llave < nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }


    //ELIMINAR

        public void eliminar(int llave) {
        raiz = eliminarNodo(raiz, llave);
    }

    private NodoAVL eliminarNodo(NodoAVL nodo, int llave) {
        if (nodo == null) {
            return null;
        }

        if (llave < nodo.getLlave()) {
            nodo.setHijoIzquierdo(eliminarNodo(nodo.getHijoIzquierdo(), llave));
        } else if (llave > nodo.getLlave()) {
            nodo.setHijoDerecho(eliminarNodo(nodo.getHijoDerecho(), llave));
        } else {

            // Nodo con un hijo o sin hijos
            if (nodo.getHijoIzquierdo() == null || nodo.getHijoDerecho() == null) {
                NodoAVL temp = (nodo.getHijoIzquierdo() != null) ? nodo.getHijoIzquierdo() : nodo.getHijoDerecho();

                if (temp == null) {
                    // Sin hijos
                    return null;
                } else {
                    // Un hijo
                    return temp;
                }
            }

            // Nodo con dos hijos: obtener sucesor (mínimo del subárbol derecho)
            NodoAVL sucesor = getSucesor(nodo);
            nodo.setLlave(sucesor.getLlave());
            nodo.setHijoDerecho(eliminarNodo(nodo.getHijoDerecho(), sucesor.getLlave()));
        }

        //Actualizar altura
        nodo.setAltura(1 + Math.max(nodo.evaluarAltura(nodo.getHijoIzquierdo()),
                                    nodo.evaluarAltura(nodo.getHijoDerecho())));

        // Verificar balance y aplicar rotaciones
        int factor = nodo.evaluarFactorBalance(nodo);

        
        if (factor > 1 && nodo.evaluarFactorBalance(nodo.getHijoIzquierdo()) >= 0) {
            return nodo.rotarDerecha(nodo);
        }

        
        if (factor > 1 && nodo.evaluarFactorBalance(nodo.getHijoIzquierdo()) < 0) {
            return nodo.rotarIzquierdaDerecha(nodo);
        }

        
        if (factor < -1 && nodo.evaluarFactorBalance(nodo.getHijoDerecho()) <= 0) {
            return nodo.rotarIzquierda(nodo);
        }

 
        if (factor < -1 && nodo.evaluarFactorBalance(nodo.getHijoDerecho()) > 0) {
            return nodo.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }

    //Sucesor: el mínimo del subárbol derecho
    private NodoAVL getSucesor(NodoAVL nodo) {
        NodoAVL actual = nodo.getHijoDerecho();
        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }
        return actual;
    }

   
    //En orden
    
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(NodoAVL nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.getHijoIzquierdo());
            System.out.print(nodo.getLlave() + " ");
            inOrdenRec(nodo.getHijoDerecho());
        }
    }
}

