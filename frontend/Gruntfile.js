module.exports = function(grunt) {

	var tmp = "tmp/";
	var tmpLib = tmp + "/lib";

	grunt.initConfig({
		backendPath: '../backend/public/app',
		jade: {
			all: {
				options: {
					pretty: false
				},
				files: {

					'<%=backendPath%>/index.html': 'src/view/index.jade',
					'<%=backendPath%>/home/inicial.html': 'src/view/home/inicial.jade',
					'<%=backendPath%>/home/sobre.html': 'src/view/home/sobre.jade',
					'<%=backendPath%>/home/contato.html': 'src/view/home/contato.jade'
				}
			}			
		},

		copy: {
			dist: {
				files: [
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/',
						src: ['**/*.js'],
						dest: '<%=backendPath%>/js/lib'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/angular-ui-chart',
						src: ['src/chart.js'],
						dest: '<%=backendPath%>/js/lib/angular-ui-chart'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/d3',
						src: ['d3.min.js'],
						dest: '<%=backendPath%>/js/lib/d3'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/d3pie/d3pie',
						src: ['d3pie.min.js'],
						dest: '<%=backendPath%>/js/lib/d3pie'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/',
						src: ['**/*min.css'],
						dest: '<%=backendPath%>/css/lib'
					}, {
						expand: true,
						flatten: true,
						cwd: 'src/images/novo/',
						src: ['**/*.png','**/*.jpg', '**/*.gif', '**/*.ico'],
						dest: '<%=backendPath%>/img'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/',
						src: ['**/*.png', '**/*.gif'],
						dest: '<%=backendPath%>/img/lib'
					},
					{
						expand: true,
						flatten: true,
						cwd: 'bower_components/bootstrap/fonts',
						src: ['*'],
						dest: '../backend/public/app/fonts'
					},
					//agregando mais icones na bilioteca do bootstrap
					//http://fortawesome.github.io/Font-Awesome/
					{
						expand: true,
						flatten: false,
						cwd: 'bower_components/components-font-awesome',
						src: ['**/*'],
						dest: '<%=backendPath%>/css/lib/font-awesome'
					},
					{
						expand: true,
						flatten: false,
						cwd: 'src/style/novo/fonts',
						src: ['**/*'],
						dest: '<%=backendPath%>/fonts'
					},
					{
						expand: true,
						flatten: false,
						cwd: 'src/img',
						src: ['*'],
						dest: '<%=backendPath%>/img'
					}

				]
			}
		},
		concat: {			
			css: {
				src: [
					'bower_components/jqplot/jquery.jqplot.css',
					tmp + '*.css'],
				dest: '<%=backendPath%>/css/main.css'
			}
		},

		ngmin: {
			dist: {
				files: [{
					expand: true,
					src: ['src/js/**/*.js'],
					dest: tmp,
					flatten: true
				}]
			}
		},

		uglify: {
			concat: {
				options: {
					beautify: true,
					compress: false,
					preserveComments : false,
					mangle: false,
					wrap: 'app'
				},
				files: {
					'<%=backendPath%>/js/script.js': [ tmp+'app.js',tmp + '*.js']
				}
			}
		},

		clean: {
			tmp: [tmp]
		},

		less: {
			development: {
				files: [
					{
						expand: true,
						compress: true,
						flatten: true,
						cwd: 'src/style',
						src: '**/*.less',
						dest: tmp,
						ext: '.css'
					}
				]
			}
		},

		jshint: {
			all: ['Gruntfile.js', 'src/js/**/*.js']
		},

		bower: {
			install: {
				options: {
					install: true,
					verbose: true,
					copy : false
				}
			}
		}

	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-ngmin');
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-jade');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-bower-task');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-concat');

	grunt.registerTask('interface', ['copy', 'jade', 'less']);

	grunt.registerTask('css', ['less', 'copy', 'concat']);

	grunt.registerTask('js', ['bower:install', 'jshint', 'copy', 'ngmin', 'uglify']);

	grunt.registerTask('build', [/*'bower:install'*/ 'jshint', 'jade:all', 'copy', 'less', 'ngmin','uglify', 'concat', 'clean']);

	grunt.registerTask('default', [/*'bower:install'*/ 'jshint', 'jade:all', 'copy', 'less', 'ngmin','uglify', 'concat', 'clean']);

};
