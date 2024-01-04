package br.com.empresa.Empresa.domain.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_endereco")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    private String logradouro;

    private int numero;

    private String complemento;

    private String bairro;

    private String localidade;

    private String uf;

    private String cep;


    public Endereco(DadosCadastroEndereco endereco, DadosViaCep dadosViaCep) {
        this.logradouro = dadosViaCep.logradouro();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.bairro = dadosViaCep.bairro();
        this.localidade = dadosViaCep.localidade();
        this.cep = dadosViaCep.cep();
        this.uf = dadosViaCep.uf();
    }
}
