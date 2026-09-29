package br.unipar.devbackend.Controller;
import br.unipar.devbackend.Model.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;

@RestController
@RequestMapping("/veiculos")

public class VeiculoController {

    ArrayList<Veiculo> veiculos = new ArrayList<>();

    @GetMapping("/listarveiculos")
    public ArrayList<Veiculo> listarveiculos() {


        return veiculos;
    }

    @PostMapping("/criarveiculo")
    public ArrayList<Veiculo> receberveiculo(@RequestBody Veiculo veiculo) {

        veiculos.add(veiculo);

        return veiculos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> pegarveiculo(@PathVariable("id") int id) {


        for (int i = 0; i < veiculos.size(); i++) {

            Veiculo veiculo = veiculos.get(i);


            if (veiculo.getId() == (id)) {

                return ResponseEntity.ok(veiculo);

            }


        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarveiculo(@PathVariable("id") int id, @RequestBody Veiculo veiculo) {

        for (int i = 0; i < veiculos.size(); i++) {

            Veiculo novoveiculo = veiculos.get(i);

            if (novoveiculo.getId() == id) {

                veiculos.set(i, veiculo);

                return ResponseEntity.ok(veiculo);


            }


        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> deletarveiculo(@PathVariable("id") int id){


        for (int i = 0; i < veiculos.size(); i++) {

            Veiculo novoveiculo = veiculos.get(i);

            if (novoveiculo.getId() == id) {

                veiculos.remove(i);

                return ResponseEntity.ok(novoveiculo);
            }

        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/filtro")

    public ResponseEntity<ArrayList<Veiculo>> filtrarveiculos(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String ano,
            @RequestParam(required = false) String fabricante) {

        ArrayList<Veiculo> resultados = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {

            boolean corresponde = true;

            if (modelo != null && !veiculo.getModelo().equalsIgnoreCase(modelo)) {
                corresponde = false;
            }

            if (ano != null && !veiculo.getAno().equalsIgnoreCase(ano)) {
                corresponde = false;
            }

            if (fabricante != null && !veiculo.getFabricante().equalsIgnoreCase(fabricante)) {
                corresponde = false;
            }

            if (corresponde) {
                resultados.add(veiculo);
            }
        }

        return ResponseEntity.ok(resultados);
    }









}