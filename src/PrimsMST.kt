import java.util.*
import kotlin.collections.HashSet

class PrimsMST {

    var graph = Hashtable<Int, HashSet<Int>>()
    var edgeWeightHashtable = Hashtable<Edge, Int>()
    var nodesSpannedSet = HashSet<Int>()
    var nodesUnspannedMinHeap = PriorityQueue<Node>()
    var completionTime = 0

    fun calculateCompletionTime(priorityScoreHeap: PriorityQueue<Job>): Long {
        var completionTime: Long = 0
        var previousRuntime: Long = 0
        while (!priorityScoreHeap.isEmpty()) {
            var j = priorityScoreHeap.poll()
            var currentRuntime = j.runtime.toInt()
            completionTime += (previousRuntime + currentRuntime) * j.weight.toInt()
            previousRuntime = previousRuntime + currentRuntime
        }
        return completionTime
    }

    fun calculateMst(graph: Hashtable<Int, HashSet<Int>>, edgeWeightHashtable: Hashtable<Edge, Int>): Int {
        this.graph = graph
        this.edgeWeightHashtable = edgeWeightHashtable
        var startWithNode = graph.keys.first()
        nodesSpannedSet.add(startWithNode)
        processEdges(startWithNode)
        return completionTime
    }

    private fun processEdges(newNodeId: Int) {
        if (nodesSpannedSet.size < graph.size) {
            for (edgeId in graph.getValue(newNodeId)) {
                if (!nodesSpannedSet.contains(edgeId)) {
                    var edgeIdNode = Node(edgeId)
                    if (nodesUnspannedMinHeap.contains(edgeIdNode)) {
                        var existingNode = nodesUnspannedMinHeap.toArray()[nodesUnspannedMinHeap.indexOf(edgeIdNode)] as Node
                        var newNode = Node(edgeId, getEdgeWeight(Edge(newNodeId, edgeId)))
                        if(newNode.weight < existingNode.weight){
                            nodesUnspannedMinHeap.remove(existingNode)
                            nodesUnspannedMinHeap.add(newNode)
                        }
                    } else {
                        nodesUnspannedMinHeap.add(Node(edgeId, getEdgeWeight(Edge(newNodeId, edgeId))))
                    }
                }
            }

            var nodeWithShortestEdge = nodesUnspannedMinHeap.poll()
            nodesSpannedSet.add(nodeWithShortestEdge.id)
            completionTime += nodeWithShortestEdge.weight
            processEdges(nodeWithShortestEdge.id)
        }
    }

    private fun getEdgeWeight(edge: Edge): Int {
        return edgeWeightHashtable.getValue(edge)
    }
}