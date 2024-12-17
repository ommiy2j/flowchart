package com.assignment.flowchart.services;

import com.assignment.flowchart.models.Edge;
import com.assignment.flowchart.models.Node;
import com.assignment.flowchart.repository.INodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class NodeService {

    private final INodeRepository nodeRepository;

    @Autowired
    public NodeService(INodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }


    public Node getOrCreateNode(String nodeName) {
        Node node = getNode(nodeName);
        if (node == null) {
            node = createNode(nodeName);
        }
        return node;
    }


    public Node createNode(String nodeName) {
        Node node = new Node();
        node.setName(nodeName);
        return nodeRepository.save(node);
    }


    public Node getNode(String nodeName) {
        return nodeRepository.findByName(nodeName);
    }

    public Set<Edge> getAllOutgoingEdges(int id) {
        Optional<Node> node = nodeRepository.findById(id);
        return node.map(Node::getOutGoingEdges).orElse(null);
    }
}
