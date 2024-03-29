# Baek-Joon Online Judge (BOJ)
[![](https://d2gd6pc034wcta.cloudfront.net/images/logo@2x.png)](https://www.acmicpc.net)

- Site that is solving algorithm problem.
- Click on the logo above to go to that site.
- This project is written in Java.
> - Java(IntelliJ) version :  IntelliJ (2019.3.4)
> - Java(TM) SE Runtime Environment (build 1.8.0_91-b14)

<br><br>
# Judgement Environment <br>
- **Ubuntu 16.04.1 LTS 64-bit**
> **Java:**
> - compile: **javac -J-Xms128m -J-Xmx512m -encoding UTF-8 Main.java**
> - execute: **java -Xms128m -Xmx512m -Xss64m -Dfile.encoding=UTF-8 Main**
> - version : **java version "1.8.0_91"**
> - time limit : **+5sec**
> - memory limit : **+512MB**

Other languages env <br>
**ref** [other languages](https://www.acmicpc.net/help/language)


<br><br>
# Available language
**Total 57** <br>

<details markdown="1">
<summary> Commonly used </summary>
　　Java <br>
　　C++, C++11, C++14, C++17 <br>
　　C, C11 <br>
　　Python3, PyPy, PyPy3
</details>

<br><br>
# Configuration
To see a specific problem, follow the path below. <br>
**src > ${algorithm_categories} > Boj${problem_number}.java**
- The class name **must be changed to main** before submitting the code.
```text
Algorithm_BaekJoon
├── .classpath
├── .git
├── .project
├── .settings
├── LICENSE
├── README.md
├── bin
├── src
│   ├── default (algorithm category packages)
│   │   ├── Boj0000.java
...
```
<br><br>
# Boj1003 example
[1003](https://github.com/minuk8932/Algorithm_BaekJoon/blob/master/src/dynamic_programming/Boj1003.java) Solution
```java
package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1003 {						// must be changed name to 'Main' before submit.
	private static int[] chk = new int[2];
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			chk = new int[2];
			
			//fibonacci(num); - memoization to fibonacci implementation.
			sb.append(chk[0]).append(SPACE).append(chk[1]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
}
```

<br><br>
Please note that common package's class
- refer to this [link](https://github.com/minuk8932/Algorithm_BaekJoon/tree/master/src/common) such as Point, Coordinate ... so on.


<br><br>
# Judgement format

![](https://github.com/minuk8932/Algorithm_BaekJoon/blob/master/img/grading_type.png)
- Please refer to this [link](https://www.acmicpc.net/help/judge) for more information. (only korean)

<br><br>
# Reference
[![solved.ac tier](http://mazassumnida.wtf/api/generate_badge?boj=exponential_e)](https://solved.ac/exponential_e) <br><br>
<details markdown="1">
<summary> Sites </summary>
　<a href = "https://solved.ac"> SOLVED.AC </a> <b> Tier Rank </b> <br>
　<a href = "https://www.acmicpc.net/user/exponential_e"> Boj Personal Rank </a> <b> Exponential_e </b> <br>
　<a href = "https://exponential-e.tistory.com"> Exponential-e Blog </a> written by minuk8932 for <b> Algorithms and CS concepts </b> (only Korean) <br>
　<a href = "https://oeis.org"> OEIS </a> Search sequence <br>
</details>


<br><br>
# License
[Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)

<br><br>
# Contact
> - e-mail : **minuk8932@naver.com**
> - github : *https://github.com/minuk8932/Algorithm_BaekJoon/issues**
