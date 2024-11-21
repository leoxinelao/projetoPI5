package com.example.PI.controllers;

import com.example.PI.entities.Meta;
import com.example.PI.repositories.MetaRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metas")
public class MetaController {

    @Autowired
    private MetaRepository metaRepository;

    // Método para buscar as metas de um plano de carreira
    @GetMapping("/plano/{planoId}")
    public List<Meta> getMetasByPlanoId(@PathVariable Long planoId) {
        return metaRepository.findByPlanoCarreiraId(planoId);
    }

    @PutMapping("/{metaId}/status")
public Meta updateStatus(@PathVariable Long metaId, @RequestBody Map<String, String> novoStatus) {
    Meta meta = metaRepository.findById(metaId).orElseThrow(() -> new RuntimeException("Meta não encontrada"));
    meta.setStatus(novoStatus.get("status"));  // Atualiza o status da meta
    return metaRepository.save(meta);  // Salva as alterações
}

}
