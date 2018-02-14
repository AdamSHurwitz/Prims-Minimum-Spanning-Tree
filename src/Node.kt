class Node(var id: Int) : Comparable<Node> {

    var weight: Int = 0

    constructor(id: Int, weight: Int) : this(id) {
        this.weight = weight
    }

    override fun equals(other: Any?): Boolean {
        other as Node
        if (id == other.id) {
            return true
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        return 31 * id
    }

    override fun compareTo(other: Node): Int {
        if (weight > other.weight) {
            return 1
        } else {
            return -1
        }
    }

}
