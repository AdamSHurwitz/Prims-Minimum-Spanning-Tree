class Job(var id: Int, var weight: Double, var runtime: Double, var priorityScore: Double) : Comparable<Job> {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Job

        if (id != other.id) return false
        if (weight != other.weight) return false
        if (runtime != other.runtime) return false
        if (priorityScore != other.priorityScore) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + weight.hashCode()
        result = 31 * result + runtime.hashCode()
        result = 31 * result + priorityScore.hashCode()
        return result
    }

    override fun compareTo(other: Job): Int {
        //todo: choose higher weight if priorityScores match
        if (priorityScore > other.priorityScore) {
            return 1
        } else if (priorityScore == other.priorityScore) {
            if (weight > other.weight){
                return 1
            } else {
                return -1
            }
        } else {
            return -1
        }
    }
}
