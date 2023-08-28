package br.com.empresa.Empresa.domain.departamento;

public record DadosDetalhamentoDepartamento(Long id, TiposDepartamento nome) {

    public DadosDetalhamentoDepartamento(Departamento departamento){
        this(
                departamento.getId(),
                departamento.getNome());
    }

}
