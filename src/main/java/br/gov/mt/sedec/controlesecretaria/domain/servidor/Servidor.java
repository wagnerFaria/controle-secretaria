package br.gov.mt.sedec.controlesecretaria.domain.servidor;

import br.gov.mt.sedec.controlesecretaria.domain.requerimentoDeAcesso.RequerimentoDeAcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Servidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String unidadeDeLotacao;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    private String matricula;

    @NotBlank
    private String cpf;

    @NotBlank
    private String registroGeral;

    @NotBlank
    private String telefone;

    @OneToMany(mappedBy = "servidor")
    @JsonIgnore
    private List<RequerimentoDeAcesso> requerimentoDeAcessoList;

    public Servidor(ServidorDtoIncluir servidor) {
        this.id = servidor.id();
        this.nome = servidor.nome();
        this.unidadeDeLotacao = servidor.unidadeDeLotacao();
        this.dataNascimento = servidor.dataNascimento();
        this.matricula = servidor.matricula();
        this.cpf = servidor.cpf();
        this.registroGeral = servidor.registroGeral();
        this.telefone = servidor.telefone();
    }
}
