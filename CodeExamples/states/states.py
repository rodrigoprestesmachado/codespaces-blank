import pandas as pd

def generate_sequential_state_diagram(input_csv_path, output_puml_path):
    """
    Gera um diagrama de estados no formato .puml com momentos bloqueados e transições numerados sequencialmente.

    Regras:
    - Cada estado representa um tópico único.
    - Momentos bloqueados dentro do estado são numerados sequencialmente.
    - Transições entre estados são numeradas sequencialmente.
    - Se o bloqueio tiver apenas uma interação, o tempo será "0 min".
    - Transições podem incluir "interleaving" ou "interleaving and spacing" com o tempo de spacing.

    Args:
    - input_csv_path: Caminho para o arquivo CSV.
    - output_puml_path: Caminho para salvar o arquivo .puml gerado.
    """
    # Carregar o CSV
    data = pd.read_csv(input_csv_path, sep=';', encoding='utf-8')
    # Converter colunas para os formatos corretos
    data['Datetime'] = pd.to_datetime(data['Datetime'], format='%H:%M:%S - %d/%m/%Y', errors='coerce')
    data = data.sort_values(by=['Id', 'Datetime'])  # Ordenar por estudante e tempo

    # Calcular a duração entre interações em minutos
    data['Time_Diff'] = data['Datetime'].diff().dt.total_seconds().fillna(0) / 60

    # Identificar momentos bloqueados
    data['Block_Number'] = (data['Time_Diff'] > 60).cumsum()

    # Iniciar o conteúdo do PUML
    puml_content = "@startuml\n\n"

    # Definir estados por tópico
    topics = data['Topic'].unique()
    for topic in topics:
        if pd.notna(topic):
            alias = topic.replace(" ", "_")
            puml_content += f'state "{topic}" as {alias}\n'

    # Adicionar momentos bloqueados numerados dentro de cada estado
    block_id = 1  # Numeração sequencial para bloqueios
    grouped = data.groupby('Topic')
    for topic, group in grouped:
        if pd.isna(topic):
            continue

        alias = topic.replace(" ", "_")
        group = group.sort_values(by=['Block_Number'])  # Ordenar por bloqueio
        state_content = ""

        for _, block_group in group.groupby('Block_Number'):
            if len(block_group) > 1:
                block_time = (block_group['Datetime'].iloc[-1] - block_group['Datetime'].iloc[0]).total_seconds() / 60
            else:
                block_time = 0  # Apenas uma interação no bloqueio
            block_actions = ', '.join(block_group['Classification'].dropna().unique())
            time_string = f"{block_time:.1f} min"
            state_content += f"{block_id}: {block_actions} - {time_string}\\n"
            block_id += 1

        puml_content += f'{alias} : {state_content.strip()}\n'

    # Criar transições numeradas sequencialmente
    previous_topic = None
    previous_time = None
    transition_id = 1  # Numeração sequencial para transições
    for _, row in data.iterrows():
        current_topic = row['Topic']
        time_diff = row['Time_Diff']

        if pd.notna(current_topic) and current_topic != previous_topic:
            if previous_topic:
                previous_alias = previous_topic.replace(" ", "_")
                current_alias = current_topic.replace(" ", "_")
                if previous_time > 60:
                    puml_content += f'{previous_alias} --> {current_alias} : {transition_id}: interleaving and spacing ({previous_time:.1f} min)\n'
                else:
                    puml_content += f'{previous_alias} --> {current_alias} : {transition_id}: interleaving ({previous_time:.1f} min)\n'
                transition_id += 1
            previous_topic = current_topic
            previous_time = time_diff

    # Finalizar o diagrama
    puml_content += "\n@enduml"

    # Escrever no arquivo de saída
    with open(output_puml_path, 'w') as file:
        file.write(puml_content)

    print(f"Arquivo .puml gerado em: {output_puml_path}")


# Exemplo de uso
input_csv = "2result.csv"  # Substitua pelo caminho do seu arquivo CSV
output_puml = "sequential_state_diagram.puml"  # Substitua pelo caminho do arquivo .puml gerado

generate_sequential_state_diagram(input_csv, output_puml)