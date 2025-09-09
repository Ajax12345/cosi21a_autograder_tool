import os, subprocess, re
import json

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

def main_proc() -> None:
    root = '/Users/jamespetullo/Downloads/ResheffAbigail-PA0'
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


    
    print(json.dumps(all_groups , indent=4))
    print('passed:', successful)
    print('failed:', failed)

if __name__ == '__main__':
    main_proc()


