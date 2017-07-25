module.exports = function(grunt) {
  require('jit-grunt')(grunt);

  grunt.initConfig({
    less: {
      development: {
        options: {
          compress: true,
          yuicompress: true,
          optimization: 2
        },
        files: {
          // destination file and source file       
          "angular/src/assets/css/theme2.0.3.css": "less/theme2.less",
          "prototype/css/theme2.0.3.css": "less/theme2.less",
          "src/main/webapp/css/headroom.css": "less/headroom.less",
          "prototype/css/headroom.css": "less/headroom.less" 
        }
      }
    },
    watch: {
      styles: {
        files: ['less/**/*.less'], // which files to watch
        tasks: ['less'],
        options: {
          nospawn: true
        }
      }
    }
  });

  grunt.registerTask('default', ['less', 'watch']);
};