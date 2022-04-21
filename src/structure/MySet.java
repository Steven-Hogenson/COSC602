package structure;

/**
 * @author Steven Hogenson on 3/15/2022
 * @project StevenHogenson_Java
 */
public class MySet implements Cloneable {
    MyArrayList setContainer;


    public MySet() {
        setContainer = new MyArrayList();
    }

    /**
     * Gets the size of a MySet object (number of elements in the set)
     *
     * @return int of the size of the MySet
     */
    public int cardinality() {
        return this.setContainer.size();
    }

    /**
     * Creates a new set of the elements within a given MySet without the elements in B
     *
     * @param B the set to check for elements to remove
     * @return a MySet containing all elements in this without any elements from B
     */
    public MySet complement(MySet B) {
        MySet result = new MySet();
        for (int i = 0; i < this.cardinality(); i++) {
            if (!B.contains(this.setContainer.elementAt(i))) {
                result.add(this.setContainer.elementAt(i));
            }
        }
        return result;
    }

    /**
     * Creates a new set of the shared elements that appear in bot a given MySet and B
     *
     * @param B MySet to check for shared elements with
     * @return a new MySet containing the set of shared elements of the 2 MySets
     */
    public MySet intersection(MySet B) {
        MySet result = new MySet();
        for (int i = 0; i < B.cardinality(); i++) {
            if (this.contains(B.setContainer.elementAt(i))) {
                result.add(B.setContainer.elementAt(i));
            }
        }
        return result;
    }

    /**
     * Creates a new set of the elements that appear only in 1 of a given MySet or B
     *
     * @param B MySet to check for unshared elements with
     * @return a new MySet containing all present but unshared elements between the 2 MySets
     */
    public MySet union(MySet B) {
        MySet result = new MySet();
        result.setContainer = this.setContainer.clone();
        for (int i = 0; i < B.cardinality(); i++) {
            result.add(B.setContainer.elementAt(i));
        }
        return result;

    }

    /**
     * Checks if all elements in a given MySet are present in B
     *
     * @param B MySet to check if all elements are in it
     * @return boolean based on if B contains all elements in a given MySet
     */
    public boolean subsetOf(MySet B) {
        for (int i = 0; i < this.cardinality(); i++) {
            if (!B.contains(this.setContainer.elementAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the symmetric difference of two sets as a new set
     *
     * @param B set to compare to
     * @return a MySet containing the symmetric difference **(this set – B) U (B – this set)
     */
    public MySet symmetricDifference(MySet B) {
        return this.complement(B).union(B.complement(this));
    }

    /**
     * Adds a new object to a MySet only if it does not already contain said object
     *
     * @param o the object to add to a MySet
     */
    public void add(Object o) {
        if (!this.contains(o)) {
            this.setContainer.append(o);
        }

    }

    /**
     * Checks if the object o already exists in a given MySet
     *
     * @param o the object to check for in the MySet
     * @return boolean based on if the object currently exists in a MySet
     */
    private boolean contains(Object o) {
        return this.setContainer.contains(o);
    }


    /**
     * Prints a String representation of a given MySet
     *
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("""
                ++++++++++++++++++++++++++++++++++++++++++
                The current set contains the following:\s
                """);
        str.append("size = ").append(setContainer.size()).append("\n");

        for (int i = 0; i < setContainer.size(); ++i) {
            str.append(setContainer.elementAt(i)).append("\t");
            if ((i + 1) % 5 == 0)
                str.append("\n");
        }
        str.append("\n+++++++++++++++++++++++++++++++++++++++++++++\n");
        return str.toString();
    }

}
