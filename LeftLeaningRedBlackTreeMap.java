import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LeftLeaningRedBlackTreeMap<K extends Comparable<? super K>, V> {

    private int size;
    private Node<K, V> root;

    public LeftLeaningRedBlackTreeMap() {
        this.size = 0;
        this.root = null;
    }

    public V put(K key, V value) throws NullPointerException {
        Node<K, V> node = new Node<>(Color.RED, key, value, null, null, null);
        Node<K, V> root_holder = this.root;
        this.size++;
        if (this.root == null) {
            node.setColor(Color.BLACK);
            this.root = node;
            return null;
        }
        while (true) {
            if (key.compareTo(this.root.getKey()) == 0) {
                V previous = this.root.getValue();
                this.root.setValue(value);
                this.size--;
                return previous;
            } else if (key.compareTo(this.root.getKey()) < 0) {
                if (this.root.getLeftChild() == null) {
                    this.root.setLeftChild(node);
                    node.setParent(this.root);
                    this.root = root_holder;
                    transformTree(this.root);
                    return null;
                } else {
                    this.root = this.root.getLeftChild();
                }
            } else {
                if (this.root.getRightChild() == null) {
                    this.root.setRightChild(node);
                    node.setParent(this.root);
                    this.root = root_holder;
                    transformTree(this.root);
                    return null;
                } else {
                    this.root = this.root.getRightChild();
                }
            }
        }
    }

    public void transformTree(Node<K, V> root) {

    }

    public void clear() {
        this.size = 0;
        if (this.root != null) {
            Node<K, V> left = this.root.getLeftChild();
            Node<K, V> right = this.root.getRightChild();
            if (left != null) {
                this.root = left;
                clear();
            }
            if (right != null) {
                this.root = right;
                clear();
            }
            this.root = null;
        }
    }

    public boolean containsKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            return false;
        }
        Node<K, V> root_holder = this.root;
        while (true) {
            if (key.compareTo(this.root.getKey()) == 0) {
                this.root = root_holder;
                return true;
            } else if (key.compareTo(this.root.getKey()) < 0) {
                if (this.root.getLeftChild() == null) {
                    this.root = root_holder;
                    return false;
                } else {
                    this.root = this.root.getLeftChild();
                }
            } else {
                if (this.root.getRightChild() == null) {
                    this.root = root_holder;
                    return false;
                } else {
                    this.root = this.root.getRightChild();
                }
            }
        }
    }

    public boolean containsValue(V value) {
        if (this.root == null) {
            return false;
        }
        if (findValue(value, this.root) == 0) {
            return false;
        }
        return true;
    }

    /* helper function for containsValue */
    public int findValue(V value, Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        if (node.getValue().equals(value)) {
            return 1;
        }
        return (findValue(value, node.getLeftChild())) + (findValue(value, node.getRightChild()));
    }

    public V get(K key) throws NullPointerException {
        if (!containsKey(key)) {
            return null;
        }
        Node<K, V> root_holder = this.root;
        while (true) {
            if (key.compareTo(this.root.getKey()) == 0) {
                V answer = this.root.getValue();
                this.root = root_holder;
                return answer;
            } else if (key.compareTo(this.root.getKey()) < 0) {
                if (this.root.getLeftChild() == null) {
                    this.root = root_holder;
                    return null;
                } else {
                    this.root = this.root.getLeftChild();
                }
            } else {
                if (this.root.getRightChild() == null) {
                    this.root = root_holder;
                    return null;
                } else {
                    this.root = this.root.getRightChild();
                }
            }
        }
    }

    public K firstKey() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<K, V> root_holder = this.root;
        while (root.getLeftChild() != null) {
            root = root.getLeftChild();
        }
        K key = root.getKey();
        root = root_holder;
        return key;
    }

    public K lastKey() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<K, V> root_holder = this.root;
        while (root.getRightChild() != null) {
            root = root.getRightChild();
        }
        K key = root.getKey();
        root = root_holder;
        return key;
    }

    public K ceilingKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            return null;
        }
        Node<K, V> root_holder = this.root;
        K answer = null;
        while (true) {
            if (key.compareTo(this.root.getKey()) == 0) {
                answer = this.root.getKey();
                this.root = root_holder;
                return answer;
            } else if (key.compareTo(this.root.getKey()) < 0) {
                answer = this.root.getKey();
                if (this.root.getLeftChild() == null) {
                    this.root = root_holder;
                    return answer;
                } else {
                    this.root = this.root.getLeftChild();
                }

            } else {
                if (this.root.getRightChild() != null) {
                    this.root = this.root.getRightChild();
                } else {
                    this.root = root_holder;
                    return answer;
                }
            }
        }
    }

    public K floorKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            return null;
        }
        Node<K, V> root_holder = this.root;
        K answer = null;
        while (true) {
            if (key.compareTo(this.root.getKey()) == 0) {
                answer = this.root.getKey();
                this.root = root_holder;
                return answer;
            } else if (key.compareTo(this.root.getKey()) > 0) {
                answer = this.root.getKey();
                if (this.root.getRightChild() == null) {
                    this.root = root_holder;
                    return answer;
                } else {
                    this.root = this.root.getRightChild();
                }

            } else {
                if (this.root.getLeftChild() != null) {
                    this.root = this.root.getLeftChild();
                } else {
                    this.root = root_holder;
                    return answer;
                }
            }
        }
    }

    public K higherKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            return null;
        }
        Node<K, V> root_holder = this.root;
        K answer = null;
        while (true) {
            if (key.compareTo(this.root.getKey()) < 0) {
                answer = this.root.getKey();
                if (this.root.getLeftChild() == null) {
                    this.root = root_holder;
                    return answer;
                } else {
                    this.root = this.root.getLeftChild();
                }

            } else {
                if (this.root.getRightChild() != null) {
                    this.root = this.root.getRightChild();
                } else {
                    this.root = root_holder;
                    return answer;
                }
            }
        }
    }

    public K lowerKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            return null;
        }
        Node<K, V> root_holder = this.root;
        K answer = null;
        while (true) {
            if (key.compareTo(this.root.getKey()) > 0) {
                answer = this.root.getKey();
                if (this.root.getRightChild() == null) {
                    this.root = root_holder;
                    return answer;
                } else {
                    this.root = this.root.getRightChild();
                }

            } else {
                if (this.root.getLeftChild() != null) {
                    this.root = this.root.getLeftChild();
                } else {
                    this.root = root_holder;
                    return answer;
                }
            }
        }
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return this.size;
    }

    public boolean equals(Object object) {
        if (object instanceof LeftLeaningRedBlackTreeMap) {
            int answer = validateTree(this.root, ((LeftLeaningRedBlackTreeMap) object).root);
            if (answer == size() && answer == ((LeftLeaningRedBlackTreeMap) object).size()) {
                return true;
            }
        }
        return false;
    }

    public int validateTree(Node<K, V> root1, Node<K, V> root2) {
        int equals = 0;
        if (root1 == null || root2 == null) {
            return 0;
        }
        if ((root1.getKey().compareTo(root2.getKey()) == 0) &&
                (root1.getValue().equals(root2.getValue())) &&
                (root1.getColor().equals(root2.getColor()))) {
            equals = 1;
        }
        return equals +
                validateTree(root1.getLeftChild(), root2.getLeftChild()) +
                validateTree(root1.getRightChild(), root2.getRightChild());
    }

    public String toString() {
        if (this.root == null) {
            return "{}";
        }
        String answer = "{";
        if (this.root.getLeftChild() == null && this.root.getRightChild() == null) {
            answer = answer + this.root.toString() + "}";
        }
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node<K, V> temp = queue.poll();
            answer = answer + temp.toString() + ", ";
            if (temp.getLeftChild() != null) {
                queue.add(temp.getLeftChild());
            }
            if (temp.getRightChild() != null) {
                queue.add(temp.getRightChild());
            }
        }
        answer = answer.substring(0, (answer.length() - 2));
        answer = answer + "}";
        return answer;
    }

}
