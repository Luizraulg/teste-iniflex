import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.math.RoundingMode;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

     // 3.2 - Remover o funcionário João
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // Formatadores
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("pt", "BR"));
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');

        DecimalFormat formatadorNumero = new DecimalFormat("#,##0.00", simbolos);

        // 3.3 - Imprimir todos os funcionários com informações formatadas
        System.out.println("\n3.3 - Funcionários cadastrados:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome()
                    + " | Data de nascimento: " + funcionario.getDataNascimento().format(formatadorData)
                    + " | Salário: " + formatadorNumero.format(funcionario.getSalario())
                    + " | Função: " + funcionario.getFuncao());
        }
     // 3.4 - Aplicar aumento de 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        }
        System.out.println("\n3.4 - Funcionários com aumento de 10%:");

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome()
                    + " | Salário: " + formatadorNumero.format(funcionario.getSalario()));
        }
     // 3.5 - Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {

            String funcao = funcionario.getFuncao();

            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }

            funcionariosPorFuncao.get(funcao).add(funcionario);
        }
     // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\n3.6 - Funcionários agrupados por função:");

        for (String funcao : funcionariosPorFuncao.keySet()) {

            System.out.println("\nFunção: " + funcao);

            for (Funcionario funcionario : funcionariosPorFuncao.get(funcao)) {

                System.out.println("Nome: " + funcionario.getNome()
                        + " | Salário: " + formatadorNumero.format(funcionario.getSalario()));
            }
        }
     // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\n3.8 - Funcionários que fazem aniversário nos meses 10 e 12:");

        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();

            if (mes == 10 || mes == 12) {
                System.out.println("Nome: " + funcionario.getNome()
                        + " | Data de nascimento: " + funcionario.getDataNascimento().format(formatadorData));
            }
        }
     // 3.9 - Funcionário com maior idade
        Funcionario funcionarioMaisVelho = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getDataNascimento()
                    .isBefore(funcionarioMaisVelho.getDataNascimento())) {

                funcionarioMaisVelho = funcionario;
            }
        }

        int idade = LocalDate.now().getYear()
                - funcionarioMaisVelho.getDataNascimento().getYear();

        System.out.println("\n3.9 - Funcionário com maior idade:");
        System.out.println("Nome: " + funcionarioMaisVelho.getNome()
                + " | Idade: " + idade);
        
     // 3.10 - Funcionários em ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("\n3.10 - Funcionários em ordem alfabética:");

        for (Funcionario funcionario : funcionarios) {

            System.out.println(funcionario.getNome());
        }
        
     // 3.11 - Total dos salários
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {

            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        System.out.println("\n3.11 - Total dos salários:");
        System.out.println("Total: " + formatadorNumero.format(totalSalarios));
        
     // 3.12 - Quantos salários mínimos cada funcionário ganha
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\n3.12 - Quantidade de salários mínimos por funcionário:");

        for (Funcionario funcionario : funcionarios) {

        	BigDecimal quantidadeSalarios = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.println(funcionario.getNome()
                    + " ganha "
                    + quantidadeSalarios
                    + " salários mínimos");
        }
    }
}