run_all_tests:
	make -j java_tests python_tests dotnet_tests ruby_tests

dotnet_tests:
	cd dotnet/SimpleSauce.Test/ && dotnet test -v n

java_tests:
	cd java/main && mvn clean test -Dmaven.javadoc.skip=true;

junit4_tests:
	cd java/junit4 && mvn clean test -Dmaven.javadoc.skip=true;

junit5_tests:
	cd java/junit5 && mvn clean test -Dmaven.javadoc.skip=true;

python_tests:
	python -m pip install --upgrade pip && cd python && pip install -r requirements.txt && pytest;

ruby_tests:
	cd ruby && bundle install && bundle exec rake;
