package com.example.QuanLyDuAn.Controller;

import com.example.QuanLyDuAn.DTO.BoardDTO;
import com.example.QuanLyDuAn.Entity.Board;
import com.example.QuanLyDuAn.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // Retrieve all boards by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Board>> getBoardsByProjectId(@PathVariable Integer projectId) {
        List<Board> boards = boardService.getBoardsByProjectId(projectId);
        return ResponseEntity.ok(boards);
    }

    // Add a new board (using DTO)
    @PostMapping
    public ResponseEntity<Board> addBoard(@RequestBody BoardDTO boardDTO) {
        Board newBoard = boardService.addBoard(boardDTO);
        return ResponseEntity.ok(newBoard);
    }

    // Update a board by ID (using DTO)
    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Integer boardId, @RequestBody BoardDTO boardDTO) {
        Board updatedBoard = boardService.updateBoard(boardId, boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    // Delete a board by ID
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
