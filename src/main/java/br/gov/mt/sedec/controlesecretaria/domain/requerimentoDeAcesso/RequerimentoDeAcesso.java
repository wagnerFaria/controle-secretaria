package br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso;

import br.gov.mt.sedec.controlesecretaria.domain.acesso.Acesso;
import br.gov.mt.sedec.controlesecretaria.domain.servidor.Servidor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RequerimentoDeAcesso {


    public RequerimentoDeAcesso(RequerimentoDeAcessoDtoIncluir requerimento) {
        this.servidor = new Servidor(requerimento.servidor());
        this.acessoList = new ArrayList<>();
        this.nomeDoChefeImediato = requerimento.nomeDoChefeImediato();
        this.cargoDoChefeImediato = requerimento.cargoDoChefeImediato();
        this.nomeCoordenadorDaGestaoDePessoas = requerimento.nomeCoordenadorDaGestaoDePessoas() != null ? requerimento.nomeCoordenadorDaGestaoDePessoas() : "Sonia Gomes";
        this.nomeCoordenadorDaTi = requerimento.nomeCoordenadorDaTi() != null ? requerimento.nomeCoordenadorDaTi() : "Wagner Faria do Amaral";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servidor", nullable = false, referencedColumnName = "id")
    private Servidor servidor;

    @OneToMany(mappedBy = "requerimentoDeAcesso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Acesso> acessoList;

    @NotBlank
    private String nomeDoChefeImediato;

    @NotBlank
    private String cargoDoChefeImediato;

    @NotBlank
    private String nomeCoordenadorDaGestaoDePessoas;

    @NotBlank
    private String nomeCoordenadorDaTi;
}
