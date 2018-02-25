# 백준 온라인 저지 (Baek-Joon Online Judge)
[![](https://d2gd6pc034wcta.cloudfront.net/images/logo.png)](https://www.acmicpc.net)

- 프로그래밍 문제를 풀고 온라인으로 채점 받을 수 있는 곳입니다.
- 위의 로고 클릭시 해당 사이트로 이동합니다.
- 현 프로젝트는 Java로 작성되었습니다.
> - Java(eclipse) version :  Neon.3 Release (4.6.3)
> - Java(TM) SE Runtime Environment (build 1.8.0_91-b14)

<br><br>
# 채점 환경
백준 온라인 저지의 **채점 환경**은 아래와 같습니다.
- **Ubuntu 16.04.1 LTS 64-bit**
> **Java:**
> - 컴파일: **javac -J-Xms128m -J-Xmx512m -encoding UTF-8 Main.java**
> - 실행: **java -Xms128m -Xmx512m -Xss64m -Dfile.encoding=UTF-8 Main**
> - 버젼 : **java version "1.8.0_91"**
> - 시간제한 : **+5초**
> - 메모리제한 : **+512MB**

Java 이외의 채점 환경은 [여기](https://www.acmicpc.net/help/language)를 통해 확인해주세요.


<br><br>
# 사용 가능 프로그래밍 언어
백준 온라인 저지의 사용 가능한 **프로그래밍 언어**는 총 57가지 입니다.
> 주 사용 언어
> - **Java**
> - **C++**, **C++11**, **C++14**, **C++17**
> - **C**, **C11**
> - **Python3**, **PyPy**, **PyPy3**

<br><br>
# 프로젝트 구성
**Algorithm_BaekJoon**는 다음과 같이 구성되어 있습니다. 코드를 보시려면 **src > 알고리즘 분류 > Boj문제번호.java**를 보시면 됩니다.
- 단 문제 제출시 반드시 클래스명을 Main으로 바꾼 후 제출해야합니다.
```text
Algorithm_BaekJoon
├── .classpath (클래스패스 파일)
├── .git (git 디렉토리)
├── .project (프로젝트 파일)
├── .settings (설정 디렉토리)
├── LICENSE (프로젝트 라이센스 파일)
├── README.md (프로젝트 설명서 파일)
├── bin (컴파일 된 소스코드 디렉토리)
├── src (소스코드 디렉토리)
│   ├── default (알고리즘 분류 패키지)
│   │   ├── Boj문제번호 (소스코드 Java 파일)
...
```
<br><br>
# 1003번 예제 소스
Java를 이용한 [1003번 문제 풀이](https://github.com/minuk8932/Algorithm_BaekJoon/blob/master/src/dynamic_programming/Boj1003.java)는 아래와 같습니다.
```java
package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1003 {                      // 제출 전 반드시 클래스명을 Main으로 바꾸어 주세요.
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

			fibonacci(num);
			sb.append(chk[0]).append(SPACE).append(chk[1]).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
  // fibonacci 메소드 생략..
}
```

<br><br>
# 채점 형식
백준 온라인 저지의 **채점 형식**은 6가지 입니다.<br>
![](https://github.com/minuk8932/Algorithm_BaekJoon/blob/master/img/grading_type.png)
> - **기다리는 중**: 채점이 밀려서 아직 채점이 되지 않은 상태입니다. 조금만 기다려 보세요.
> - **재채점을 기다리는 중**: 재채점을 기다리는 중입니다.
> - **컴파일 하는 중**: 컴파일 하는 중입니다.
> - **채점중**: 채점을 하는 중입니다.
> - **맞았습니다!!**: 제출한 프로그램이 모든 테스트 케이스를 통과했을 때 입니다. 즉, 정답입니다!
> - **출력 형식이 잘못되었습니다**: 출력 결과는 정답과 유사하나, 공백, 빈 줄과 같은 문제로 인해서 출력 결과가 일치하지 않은 경우 입니다.
> - **틀렸습니다**: 출력 결과가 정답과 다른 경우 입니다.
> - **시간 초과**: 프로그램이 제한된 시간이내에 끝나지 않은 경우입니다. 이런 경우에는 채점을 중간에 중단하므로, 정답이 맞는지 아닌지는 알 수가 없습니다.
> - **메모리 초과**: 프로그램이 허용된 메모리보다 많은 메모리를 사용했을 경우입니다.
> - **출력 초과**: 너무 많은 출력이 발생하는 경우입니다. 주로 프로그램이 무한 루프에 빠졌을 때 발생합니다. 출력 제한은 1MB 입니다.
> - **런타임 에러**: 실행 도중에 'segmentation fault', 'floating point exception', 'used forbidden functions', 'tried to access forbidden memories' 등의 에러가 발생하여서 프로그램이 종료된 경우 입니다.
> - **컴파일 에러**: 컴파일 하지 못한 경우입니다. Warning Message는 에러 메시지가 아닙니다. 채점 결과를 클릭하면 컴파일 에러 메시지를 볼 수 있습니다.

<br><br>
# 더 많은 서비스
백준 온라인 저지는 여러가지 서비스를 제공합니다. <br>
> - **대회** : 백준 온라인 저지에서 주최하는 프로그래밍 대회입니다. 제출된 문제는 추후 온라인 저지에 추가됩니다. 메뉴 상단의 대회탭을 통해 확인하실 수 있습니다.
> - **랭킹(학교 등록)** : 개인별 랭킹이 기록됩니다. 학교 등록을 하면 학교 랭킹에도 참여 할 수 있고, 교내 랭킹도 확인 가능합니다. 메뉴 상단의 랭킹탭을 통해 확인하실 수 있습니다.
> - **문제집** : 온라인 저지를 사용하는 사용자들이 직접 여러 문제를 묶어 만든 문제집입니다. 사이트내 존재하는 문제들로 직접 풀어 볼 수 있습니다. 메뉴 상단의 문제집탭을 통해 확인 하실 수 있습니다.
- 이외의 더 많은 정보가 필요하시면 상단의 **백준 온라인 저지** 로고를 클릭해 주세요.

<br><br>
# 라이센스
본 프로젝트는 [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)를 따릅니다.

<br><br>
# 문의사항
기타 문의사항이 있으실 경우 아래의 **문의 수단**으로 연락해주세요.
> **문의 수단**
> - 메일 : **minuk8932@naver.com**
> - github : *https://github.com/minuk8932/Algorithm_BaekJoon/issues**
