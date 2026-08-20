# Movish API Documentation

Documentation site for Movish API and the services that support Movish App.

## Development

Requirements:

- Node.js
- pnpm 9.15.9

Install dependencies and start the local server:

```bash
pnpm install
pnpm dev
```

The site is available at `http://localhost:4321/Movish/`.

## Commands

```bash
pnpm dev      # Start the development server
pnpm check    # Run Astro diagnostics
pnpm build    # Build the production site
pnpm preview  # Preview the production build
```

## Documentation

English content is stored in `src/content/docs/`.
Spanish content follows the mirrored structure in `src/content/docs/es/`.

Use Markdown for regular pages and MDX when Astro components are needed. Documentation pages should include frontmatter with a `title` and `description`.

## Project Structure

```text
src/
├── assets/                  # Logos, images, and visual assets
├── components/              # Shared Astro components
├── config/                  # Site, menu, locale, and sidebar settings
├── content/docs/            # Documentation content
├── content/docs/es/         # Spanish documentation mirror
├── lib/                     # Shared utilities
└── styles/                  # Global styles
```

## Deployment

Build the site with:

```bash
pnpm build
```

The production output is generated in `dist/`.

<!-- TODO: Add the final deployment workflow. -->

## License

This project uses the license included in [LICENSE](LICENSE).

## Attribution

This site is an adaptation of the DocKit Astro template by Themefisher. Original template: [themefisher.com/products/dockit-astro](https://themefisher.com/products/dockit-astro).
