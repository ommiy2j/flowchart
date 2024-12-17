package com.assignment.flowchart.controller;


import com.assignment.flowchart.dto.ErrorResponse;
import com.assignment.flowchart.dto.FlowChartRequestDTO;
import com.assignment.flowchart.exception.InvalidDataException;
import com.assignment.flowchart.models.FlowChart;
import com.assignment.flowchart.services.FlowChartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flowchart")
public class FlowChartController {

    private static Logger log = LogManager.getLogger(FlowChartController.class);

    private final FlowChartService flowChartService;

    @Autowired
    public FlowChartController(FlowChartService flowChartService) {
        this.flowChartService = flowChartService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createFlowChart(@RequestBody FlowChartRequestDTO dto) {
        log.info("Received request to create a flowchart with title: {}", dto.getTitle());
        try {
            FlowChart flowChart = flowChartService.createFlowChart(dto);
            log.info("Successfully created flowchart with ID: {}", flowChart.getId());
            return ResponseEntity.ok(flowChart);
        } catch (InvalidDataException e) {
            log.error("Invalid data provided for flowchart creation: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid data: " + e.getMessage()));
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating flowchart: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ErrorResponse("An unexpected error occurred. Please try again later."));
        }
    }


    @GetMapping
    public ResponseEntity<?> getFlowChart(@RequestParam int  id) {
        try {
            return new ResponseEntity<>(flowChartService.getFlowChartById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("An unexpected error occurred while getting flowchart: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFlowChart(@RequestParam int id, @RequestBody FlowChartRequestDTO dto) {
        log.info("Received request to update a flowchart with ID: {}", id);
        try {
            FlowChart flowChart = flowChartService.updateFlowChart(id, dto);
            log.info("Successfully updated flowchart with ID: {}", flowChart.getId());
            return ResponseEntity.ok(flowChart);
        } catch (RuntimeException e) {
            log.error("An unexpected error occurred while updating flowchart: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
