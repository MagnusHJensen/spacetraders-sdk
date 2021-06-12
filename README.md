# Spactraders SDK

Spacetraders SDK is a Java API Wrapper for [SpaceTraders.io](https://spacetraders.io).

The SpaceTraders API and this SDK is still in development and subject to frequent breaking!

## Installation
To get started with using this package, you need maven.

Inside your pom.xml
```xml
<dependency>
    <groupId>dk.magnusjensen</groupId>
    <artifactId>spacetraders-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start
Have a look at the javadocs [here](https://magnushjensen.github.io/docs/spacetraders-sdk-snapshot/index.html).
```java
import dk.magnusjensen.spacetraders.*;

public class Main {
    public static void main(String[] args){
        SpaceTraders st = new SpaceTraders.Builder().setToken("token").build(); // To login to account
        SpaceTraders st = new SpaceTraders.Builder().setUsername("your preferred username").build(); // To get a new account.
        
        AccountEntity account = st.getAccount();
        System.out.println(account.getCredits());
        
        ArrayList<LoanType> loans = LoanType.getLoans(st.getToken());
        
        LoanEntity loan = loans.get(0).take(st.getToken());
        account = st.getAccount();
        System.out.println(account.getCredits());
    }
}

```

## Planned Features
- [ ] Caching
- [ ] Event System
- [ ] Ratelimiter

---
If you ever make something with the use of this wrapper, please hit me up on Discord `legenden#7526` and I will put a link to your project in here!


