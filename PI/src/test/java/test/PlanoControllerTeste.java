/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author eee
 */
import com.example.PI.PiApplication;

import com.example.PI.entities.Meta;
import com.example.PI.entities.PlanoCarreira;
import com.example.PI.repositories.PlanoRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = PiApplication.class)
@AutoConfigureMockMvc
public class PlanoControllerTeste {

    @Autowired
    private PlanoRepository planoRepository;

    // Teste de criação de um plano
     @Test
    @Transactional
    public void testCriarPlanoComMetas() {

        // Criar as metas
        Meta meta1 = new Meta();
        meta1.setDescricao("Aumentar a eficiência do código");

        Meta meta2 = new Meta();
        meta2.setDescricao("Participar de 3 treinamentos");

        // Criar o plano de carreira
        PlanoCarreira plano = new PlanoCarreira();
        plano.setNome("Plano de Carreira 202222222222224");
        plano.setCargoAtual("Desenvolvedor");
        plano.setBonificacao("1500.00");       
        plano.setMetas(Arrays.asList(meta1, meta2));

        PlanoCarreira planoSalvo = new PlanoCarreira();
        
            try {
                planoSalvo = planoRepository.save(plano);
                planoRepository.save(plano);

            } catch (Exception e) {
                e.printStackTrace();
            }

        // Verificar se o plano foi salvo corretamente
        assertNotNull(planoSalvo.getId()); // Verifica se foi gerado um ID
        assertEquals("Plano de Carreira 202222222222224", planoSalvo.getNome());
        assertEquals("Desenvolvedor", planoSalvo.getCargoAtual());
        assertEquals(2, planoSalvo.getMetas().size()); // Verifica se há duas metas
        assertEquals("Aumentar a eficiência do código", planoSalvo.getMetas().get(0).getDescricao());
    }

    
}