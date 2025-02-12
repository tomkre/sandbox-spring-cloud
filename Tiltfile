custom_build(
    ref = 'tomkre/sbs-app:1.0',
    command = './gradlew :master-service:bootBuildImage --imageName $EXPECTED_REF',
    deps = ['master-service/build.gradle.kts', 'master-service/src']
)

k8s_yaml('k8s/application.yml')
k8s_resource('sbs-deploy', port_forwards=['8082'])