class Edge(var nodeOne: Int, var nodeTwo: Int) {
    var weight: Int? = null

    constructor(nodeOne: Int, nodeTwo: Int, weight: Int?) : this(nodeOne, nodeTwo) {
        this.weight = weight
    }

    override fun equals(other: Any?): Boolean {
        other as Edge

        if (nodeOne == other.nodeOne && nodeTwo == other.nodeTwo || nodeOne == other.nodeTwo && nodeTwo == other.nodeOne) {
            return true
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        var result = nodeOne + nodeTwo
        return 31 * result
    }

}
