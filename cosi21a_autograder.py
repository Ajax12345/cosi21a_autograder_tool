import os, subprocess, re
import json, csv, yaml
import shutil, typing

def create_java_folder_structure(reformatted_path:str, root_name:str) -> None:
    os.makedirs(os.path.join(reformatted_path, root_name, 'bin', 'main'), exist_ok=True)
    os.makedirs(os.path.join(reformatted_path, root_name, 'bin', 'test'), exist_ok=True)
    os.makedirs(os.path.join(reformatted_path, root_name, 'src', 'main'), exist_ok=True)
    os.makedirs(os.path.join(reformatted_path, root_name, 'src', 'test'), exist_ok=True)
    os.makedirs(jar_file:=os.path.join(reformatted_path, root_name, 'test-lib'), exist_ok=True)
    shutil.copy('junit-platform-console-standalone-1.11.0.jar', jar_file)


def submissions_to_folder_structure(download_path:str, reformatted_path:str) -> None:
    folders_to_names = {}
    with open(os.path.join(download_path, 'gradescope', 'submission_metadata.yml')) as f:
        folders_to_names = {a:b[':submitters'][0][':name'].replace(' ', '') for a, b in yaml.safe_load(f).items()}

    
    all_submission_dirs = []
    for i in os.listdir(os.path.join(download_path, 'gradescope')):
        if i not in ['.DS_Store', 'submission_metadata.yml']:
            all_submission_dirs.append((i, os.path.join(download_path, 'gradescope', i)))
    

    for sub_name, full_path in all_submission_dirs:
        create_java_folder_structure(reformatted_path, 
                root_name:=folders_to_names.get(sub_name, sub_name))
        
        for root, dirs, files in os.walk(full_path):
            if '__MACOSX' not in root:
                for file in files:
                    if file.endswith('.java'):
                        with open(os.path.join(root, file)) as f:
                            file_contents = f.read()

                        if file.lower().endswith('test.java'):
                            with open(os.path.join(reformatted_path, root_name, 'src', 'test', file), 'w') as f:
                                f.write(file_contents)
                        
                        else:
                            with open(os.path.join(reformatted_path, root_name, 'src', 'main', file), 'w') as f:
                                f.write(file_contents)
                        

def group(l):
    result = []
    while l:
        a, b = l.pop(0)
        p = []
        while l and l[0][0] < a:
            p.append(l.pop(0))
        
        result.append({'name': b, 'children': group(p)})
        
    return result

def parse_test_case(c:str) -> dict:
    passed = '✔' in c
    name = re.findall('^[^✔✘]+', c)[0]
    message = c.replace(name, '')
    return {
        'passed': passed,
        'name': name.strip(),
        'message': message[1:]
    }

def main_proc(s_name:str, root:str) -> tuple:
    os.chdir('/Users/jamespetullo/cosi21a_autograder_tool')
    supporting = 'pa0_supporting'
    for i in os.listdir(supporting):
        with open(os.path.join(supporting, i)) as f:
            src = f.read()

        with open(os.path.join(root, 'src', 'test', i), 'w') as f:
            f.write(src)

    os.chdir(root)
    subprocess.run('mkdir -p bin'.split())
    subprocess.run(' '.join(['javac', '-d',  'bin',  '-cp', 'test-lib/junit-platform-console-standalone-1.11.0.jar', '$(find src -name "*.java")']), shell = True)
    result = subprocess.run('java -jar test-lib/junit-platform-console-standalone-1.11.0.jar -cp bin --scan-classpath'.split(), capture_output=True, text=True)
    
    print(result.stdout)
    full_text = re.sub('\x1b\[\d+m', '', result.stdout)
    successful = int(re.findall('\d+ tests* successful', full_text)[0].split()[0])
    failed = int(re.findall('\d+ tests* failed', full_text)[0].split()[0])

    lines = []
    test_cases = []
    for i in full_text.split('\n'):
        if i and i[0] in '│├╷':
            default_line = re.sub('^[^\[\w]+', '', i)
            if re.findall('\w+\(', i):
                test_cases.append(default_line)

            if re.findall('\[\d+\]', i):
                test_cases.append(default_line)

            l = re.sub('^[^\[\w]+', lambda x:'*'*len(x.group()), i)
            lines.append((len(re.findall('^\*+', l)[0]), re.sub('^\*+', '', l)))

    all_groups = []
    for i in test_cases:
        if i[0] == '[':
            all_groups[-1]['enums'].append(parse_test_case(i))
        else:
            all_groups.append({'case': parse_test_case(i), 'enums': []})


    
    #print(json.dumps(all_groups , indent=4))
    return all_groups, successful, failed


def produce_sheet(files:typing.List[str], flat_csv:bool = False) -> None:
    all_grades = []
    for f in files:
        all_groups, successfull, failed = main_proc(name:=f.split('/')[-1], f)
        final_results = {'successful': successfull, 'failed': failed}

        for i in all_groups:
            if i['enums']:
                for j in i['enums']:
                    final_results[f'{i["case"]["name"]}.{j["name"]}'] = j['passed']

            else:
                final_results[f'{i["case"]["name"]}'] = i['case']['passed']

        
        all_grades.append((name, final_results))
    

    os.chdir('/Users/jamespetullo/cosi21a_autograder_tool')

    if flat_csv:
        keys = sorted({j for _, k in all_grades for j in k},key=lambda x:(x in['successful', 'failed'], x))
        with open('results.csv', 'w') as f:
            write = csv.writer(f)
            write.writerow(['name', *keys])
            write.writerows([[name]+[j.get(k, False) for k in keys] for name, j in all_grades])

    else:
        with open('results.csv', 'w') as f:
            write = csv.writer(f)
            write.writerows([[name]+[f'{a}: {b}' for a, b in j.items()] for name, j in all_grades])



if __name__ == '__main__':
    #submissions_to_folder_structure('pa0_submissions', 'pa0_reformatted_submissions')
    produce_sheet([os.path.join(os.getcwd(), 'pa0_reformatted_submissions', i) 
        for i in os.listdir('pa0_reformatted_submissions')])


