run_all_tests:
	make -j java_tests python_tests csharp_tests ruby_tests

csharp_tests:
	echo "run csharp tests"

java_tests:
	cd java && mvn clean test;

python_tests:
	echo "run python tests"

ruby_tests:
	echo "run ruby tests"
