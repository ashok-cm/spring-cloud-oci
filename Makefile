# ----------------------------------------------------------------------------
#  Copyright (c) 2023, Oracle and/or its affiliates.
#  Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
# ----------------------------------------------------------------------------

.PHONY: format build javadocs

format:
	mvnd spotless:apply

build:
	mvnd verify -DskipTests

clean:
	mvnd clean

javadocs:
	mvnd clean package javadoc:aggregate -DskipTests=true -e
