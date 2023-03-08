package br.gov.mt.sedec.controlesecretaria.domain.acesso;

import br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso.RequerimentoDeAcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @ManyToOne()
    @JoinColumn(name = "requerimento_de_acesso", referencedColumnName = "id", nullable = false)
    private RequerimentoDeAcesso requerimentoDeAcesso;

}
