/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  plugins: [require("daisyui")],
  // Opcional: configura os temas do DaisyUI
  daisyui: {
    themes: ["light", "dark"],
  },
};
