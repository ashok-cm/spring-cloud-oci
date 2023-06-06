# Spring Cloud OCI

[![License: UPL](https://img.shields.io/badge/license-UPL-green)](https://img.shields.io/badge/license-UPL-green)

## Introduction
Spring Cloud for OCI, eases the integration with OCI services with the help of OCI Java SDK internally. It offers a convenient way to interact with OCI-provided services using well-known Spring idioms and APIs, such as the messaging or storage API. Developers can build their applications around the hosted services without having to care about infrastructure or maintenance. Spring Cloud for OCI contains auto-configuration support for OCI-managed services.

All Spring Cloud for OCI artifacts are made available through Maven Central. Developers can bootstrap their application with Spring Initializr and add the OCI Support dependency. With Spring Cloud for OCI, the developers only need to add some annotations and a small number of configurations to connect Spring Cloud applications to the OCI services.

## Checking out and building

To check out the project and build it from source, do the following:

```
git clone https://github.com/oracle/spring-cloud-oci.git
cd spring-cloud-oci
mvn package
```

To build and install jars into your local Maven cache:

```
mvn install
```

For faster builds, we recommend using [Maven Daemon](https://github.com/apache/maven-mvnd) and using following commands:

Build:
```
make build
```

Clean:
```
make clean
```

Format code:
```
make format
```

## Documentation

Generate usage documentation with below command

```
make docs
```

It generates:

* reference documentation in `docs/target/generated-docs/`
* API docs in `target/site/`

Java docs can be generated with below command

```
make javadocs
```

## Contributing

This project welcomes contributions from the community. Before submitting a pull request, please [review our contribution guide](./CONTRIBUTING.md)

## Security

Please consult the [security guide](./SECURITY.md) for our responsible security vulnerability disclosure process

## License

Copyright (c) 2023 Oracle and/or its affiliates.

Released under the Universal Permissive License v1.0 as shown at
<https://oss.oracle.com/licenses/upl/>.

