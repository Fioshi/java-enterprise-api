package br.com.empresa.Empresa.domain.departamento;

import br.com.empresa.Empresa.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_departamento")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TiposDepartamento nome;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;

    public Departamento(DadosCadastroDepartamento dados) {
        this.nome = dados.nome();
    }
}
