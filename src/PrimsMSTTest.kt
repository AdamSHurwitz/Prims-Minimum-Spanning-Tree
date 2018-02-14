import java.io.File
import java.util.*
import kotlin.collections.HashSet

object PrimsMSTTest {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Schedule Minimum Process Completion Time")
        var diffPriorityScoreHeap = PriorityQueue<Job>(Collections.reverseOrder())
        var ratioPriorityScoreHeap = PriorityQueue<Job>(Collections.reverseOrder())
        File("src/data/minWeightedSumData.txt")
                .inputStream()
                .bufferedReader()
                .useLines { lines ->
                    lines.forEach {
                        var jobValues = arrayListOf<Double>()
                        for (line in Regex("[^\\s]+").findAll(it)) {
                            jobValues.add(line.value.toDouble())
                        }
                        if (jobValues.size == 2) {
                            var weight = jobValues[0]
                            var runtime = jobValues[1]
                            diffPriorityScoreHeap.add(Job(diffPriorityScoreHeap.size + 1, weight.toDouble(), runtime.toDouble(), weight - runtime))
                            ratioPriorityScoreHeap.add(Job(diffPriorityScoreHeap.size + 1, weight.toDouble(), runtime.toDouble(), weight / runtime))
                        }
                    }
                }

        println(String.format("Optimal completion with Difference priority score: %1s",
                PrimsMST().calculateCompletionTime(diffPriorityScoreHeap).toString()))

        println(String.format("Optimal completion with Ratio priority score: %1s",
                PrimsMST().calculateCompletionTime(ratioPriorityScoreHeap).toString()))

        println("Find Minimum Spanning Tree")
        var graph = Hashtable<Int, HashSet<Int>>()
        var edgeWeightHashSet = Hashtable<Edge, Int>()

        File("src/data/primsMstData.txt")
                .inputStream()
                .bufferedReader()
                .useLines { lines ->
                    lines.forEach {
                        var edgeValues = arrayListOf<Int>()
                        for (line in Regex("[^\\s]+").findAll(it)) {
                            edgeValues.add(line.value.toInt())
                        }
                        if (edgeValues.size == 3) {

                            var nodeOne = edgeValues[0]
                            var nodeTwo = edgeValues[1]
                            var weight = edgeValues[2]
                            if (!graph.containsKey(nodeOne)) {
                                graph.put(nodeOne, hashSetOf(nodeTwo))
                            } else {
                                graph.get(nodeOne)?.add(nodeTwo)
                            }

                            if (!graph.containsKey(nodeTwo)) {
                                graph.put(nodeTwo, hashSetOf(nodeOne))
                            } else {
                                graph.get(nodeTwo)?.add(nodeOne)
                            }

                            var edge = Edge(nodeOne, nodeTwo, weight)
                            if (!edgeWeightHashSet.contains(edge)) {
                                edgeWeightHashSet.put(edge, weight)
                            }
                        }
                    }
                }
        println(String.format("Minimum Spanning Tree: %1s",
                PrimsMST().calculateMst(graph, edgeWeightHashSet)))
    }
}